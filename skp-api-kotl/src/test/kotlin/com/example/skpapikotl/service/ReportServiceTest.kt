package com.example.skpapikotl.service

import com.example.skpapikotl.constant.ReportType
import com.example.skpapikotl.domain.Report
import com.example.skpapikotl.exception.ReportNotFoundException
import com.example.skpapikotl.repository.ReportRepository
import com.example.skpapikotl.service.dto.ReportCreateDto
import com.example.skpapikotl.service.dto.ReportUpdateDto
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull

@SpringBootTest
class ReportServiceTest(
    reportRepository: ReportRepository,
    reportService: ReportService
) : BehaviorSpec({
    beforeSpec{
        reportRepository.save(Report(
            title = "테스트 제목",
            createdBy = "테스트 작성자",
            reportType = ReportType.A,
            description = null,
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
})