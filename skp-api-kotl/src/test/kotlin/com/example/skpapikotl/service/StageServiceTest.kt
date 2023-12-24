package com.example.skpapikotl.service

import com.example.skpapikotl.constant.ReportType
import com.example.skpapikotl.domain.Report
import com.example.skpapikotl.exception.StageNotFoundException
import com.example.skpapikotl.repository.ReportRepository
import com.example.skpapikotl.repository.StageRepository
import com.example.skpapikotl.service.dto.StageCreateDto
import com.example.skpapikotl.service.dto.StageUpdateDto
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.longs.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull

@SpringBootTest
class StageServiceTest(
    private val reportRepository: ReportRepository,
    private val stageService: StageService,
    private val stageRepository: StageRepository,
) : BehaviorSpec({
    given("단계별 목표 생성") {
        val report = reportRepository.save(Report(
            title = "title",
            createdBy= "spark",
            reportType = ReportType.A
        ))
        When("인풋이 멀쩡하면") {
            val stageId = stageService.create(report.id, StageCreateDto(
                startAt= "2015",
                endAt= "2016",
                createdBy= "spark",
                content= "내용"
            ))
            then("정상적으로 생성됨을 확인") {
                stageId shouldBeGreaterThan 0L
                val stage = stageRepository.findByIdOrNull(stageId)
                stage?.stage shouldBe 1L
                stage?.startAt shouldBe "2015"
                stage?.endAt shouldBe "2016"
                stage?.createdBy shouldBe "spark"
                stage?.content shouldBe "내용"
            }
            val stageId2 = stageService.create(report.id, StageCreateDto(
                startAt= "2016",
                endAt= "2018",
                createdBy= "spark",
                content= "내용2"
            ))
            then("단계 서수 정상 생성 확인") {
                val stage2 = stageRepository.findByIdOrNull(stageId2)
                stage2?.stage shouldBe 2L
            }
        }
    }
    given("단계별 업데이트"){
        val report = reportRepository.save(Report(
            title = "title",
            createdBy= "spark",
            reportType = ReportType.A
        ))
        val stageId = stageService.create(report.id, StageCreateDto(
            startAt = "2015",
            endAt = "2016",
            createdBy = "spark",
            content = "content1"
        ))
        When("정상적으로 입력") {
            val updatedId = stageService.update(stageId, StageUpdateDto(
                stage = 1,
                startAt="2016",
                endAt="2018",
                updatedBy = "spark2",
                content = "content2"
            )
            )
            then("성공적으로 수정됨을 확인") {
                val updatedStage = stageRepository.findByIdOrNull(updatedId)
                updatedStage shouldNotBe null
                updatedStage?.stage shouldBe 1L
            }
        }
        When("업데이트 대상이 틀렸을때") {
            then("해당하는 단계별 성과목표가 없다고 오류 반환"){
                shouldThrow<StageNotFoundException> {
                    stageService.update(999L, StageUpdateDto(
                        stage = 1,
                        startAt="2016",
                        endAt="2018",
                        updatedBy = "spark2",
                        content = "content2"
                    ))
                }
            }
        }
    }
    given("단계별 삭제"){
        val report = reportRepository.save(Report(
            title = "title",
            createdBy= "spark",
            reportType = ReportType.A
        ))
        val stageId = stageService.create(report.id, StageCreateDto(
            startAt = "2015",
            endAt = "2016",
            createdBy = "spark",
            content = "content1"
        ))
        When("정상적으로 삭제하면"){
            val deletedId: Long? = stageService.delete(stageId, "spark")
            then("삭제됨을 확인"){
                deletedId shouldNotBe null
                val updatedStage = stageRepository.findByIdOrNull(stageId)
                updatedStage shouldBe null
            }
        }
        When("삭제 대상을 찾지 못하면"){
            then("찾지 못했다고 오류 리턴"){
                shouldThrow<StageNotFoundException> {
                    stageService.delete(stageId, "spark")
                }
            }
        }
    }
})