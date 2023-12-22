package com.example.skpapikotl.service

import com.example.skpapikotl.constant.ReportType
import com.example.skpapikotl.controller.dto.ReportDetailResponse
import com.example.skpapikotl.domain.Report
import com.example.skpapikotl.exception.ReportNotFoundException
import com.example.skpapikotl.repository.ReportRepository
import com.example.skpapikotl.service.dto.ReportCreateDto
import com.example.skpapikotl.service.dto.ReportSearchDto
import com.example.skpapikotl.service.dto.ReportUpdateDto
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull

@SpringBootTest
class ReportServiceTest(
    private val reportRepository: ReportRepository,
    private val reportService: ReportService
) : BehaviorSpec({
    beforeSpec{
        reportRepository.saveAll(listOf(
            Report(title="title1", createdBy="creator1", reportType=ReportType.A, description = null),
            Report(title="title2", createdBy="creator1", reportType=ReportType.A, description = null),
            Report(title="title3", createdBy="creator1", reportType=ReportType.B, description = null),
            Report(title="title4", createdBy="creator1", reportType=ReportType.B, description = null),
            Report(title="title5", createdBy="creator1", reportType=ReportType.B, description = null),
            Report(title="title6", createdBy="creator2", reportType=ReportType.C, description = null),
            Report(title="title7", createdBy="creator2", reportType=ReportType.C, description = null),
            Report(title="title8", createdBy="creator2", reportType=ReportType.D, description = null),
            Report(title="title9", createdBy="creator2", reportType=ReportType.D, description = null),
            Report(title="title10", createdBy="creator2", reportType=ReportType.D, description = null),
        ))
    }
    given("새 보고서를 생성한다") {
        val newReport = ReportCreateDto(
            title = "title",
            createdBy = "createdBy",
            reportType = "A",
            description = "description",
        )
        When("정상 값이 들어오면") {
            val returnedId: Long? = reportService.create(newReport)
            then("정상적으로 생성된다") {
                returnedId shouldNotBe null
                val foundReport: Report? = reportRepository.findByIdOrNull(returnedId)
                foundReport shouldNotBe null
                foundReport?.title shouldBe "title"
                foundReport?.createdBy shouldBe "createdBy"
                foundReport?.reportType.toString() shouldBe "A"
                foundReport?.description shouldBe "description"
            }
        }
    }
    given("보고서 수정") {
        val saved: Report = reportRepository.save(Report(
            title = "title",
            createdBy = "createdBy",
            reportType = ReportType.A,
            description = "description",
        ))
        val update = ReportUpdateDto(
            title = "new title",
            updatedBy = "updatedBy",
            reportType = "B",
            description = "modified description",
        )
        When("정상적으로 수정할 경우") {
            val updatedId: Long = reportService.update(saved.id, update)
            then("정상적으로 수정 된다") {
                updatedId shouldBe saved.id
                val found = reportRepository.findByIdOrNull(updatedId)
                found shouldNotBe null
                found?.title shouldBe "new title"
                found?.updatedBy shouldBe "updatedBy"
                found?.reportType.toString() shouldBe "B"
                found?.description shouldBe "modified description"
            }
        }

        When("해당하는 보고서가 없을때") {

            then("수정할 보고서를 찾을 수 없다고 나옴") {
                shouldThrow<ReportNotFoundException> { reportService.update(999L, update) }
            }
        }
    }
    given("보고서 삭제"){
        val saved: Report = reportRepository.save(Report(
            title = "title",
            createdBy = "createdBy",
            reportType = ReportType.A,
            description = "description",
        ))
        When("정상적으로 삭제"){
            reportService.delete(saved.id, "createdBy")
            then("보고서가 잘 삭제된다"){
                val found: Report? = reportRepository.findByIdOrNull(saved.id)
                found shouldBe null
            }
        }

        When("보고서가 없을 경우"){
            then("보고서를 찾을 수 없다는 에러 발생"){
                shouldThrow<ReportNotFoundException> { reportService.delete(999L, "createdBy") }
            }
        }
    }
    given("게시글 상세 조회"){
        val saved: Report = reportRepository.save(Report(
            title = "title",
            createdBy = "createdBy",
            reportType = ReportType.A,
            description = "description",
        ))
        When("정상 조회시"){
            val found: ReportDetailResponse = reportService.getReport(saved.id)!!
            then("정상적으로 리턴 된다"){
                found shouldNotBe null
                found.title shouldBe "title"
                found.createdBy shouldBe "createdBy"
                found.reportType.toString() shouldBe "A"
                found.description shouldBe "description"
            }
        }
        When("보고서 없을때"){
            then("보고서 찾을 수 없다고 에러"){
                shouldThrow<ReportNotFoundException> { reportService.getReport(999L) }
            }
        }
    }
    given("게시글 목록 조회") {
        When("모두 조회시") {
            reportRepository.save(Report(
                title="last title",
                createdBy = "last creator",
                reportType = ReportType.D,
            ))
            val page = reportService.findPageBy(
                PageRequest.of(0, 5),
                ReportSearchDto(),
            )
            then("페이지 정상적으로 반환") {
                page.number shouldBe 0
                page.size shouldBe 5
                page.content.size shouldBe 14
                page.content[0].title shouldBe "last title"
                page.content[0].createdBy shouldBe "last creator"
                page.content[0].reportType.toString() shouldBe "D"
            }
        }
        When("작성자로 조회시") {
            val page = reportService.findPageBy(
                PageRequest.of(0, 5),
                ReportSearchDto(createdBy = "creator1"),
            )
            then("페이지 정상적으로 반환") {
                page.number shouldBe 0
                page.size shouldBe 5
                page.content.size shouldBe 5
                page.content[0].title shouldBe "title5"
                page.content[0].createdBy shouldBe "creator1"
                page.content[0].reportType.toString() shouldBe "B"
            }
        }
        When("보고서 타입으로 조회시") {
            val page = reportService.findPageBy(
                PageRequest.of(0, 5),
                ReportSearchDto(reportType = "C"),
            )
            then("페이지 정상적으로 반환") {
                page.number shouldBe 0
                page.size shouldBe 5
                page.content.size shouldBe 2
                page.content[0].title shouldBe "title7"
                page.content[0].createdBy shouldBe "creator2"
                page.content[0].reportType.toString() shouldBe "C"
            }
        }
    }
})