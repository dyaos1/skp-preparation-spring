package com.example.skpapikotl.service

import com.example.skpapikotl.constant.ReportType
import com.example.skpapikotl.domain.Goal
import com.example.skpapikotl.domain.Report
import com.example.skpapikotl.exception.GoalNotFoundException
import com.example.skpapikotl.exception.ReportNotFoundException
import com.example.skpapikotl.repository.GoalRepository
import com.example.skpapikotl.repository.ReportRepository
import com.example.skpapikotl.service.dto.GoalCreateDto
import com.example.skpapikotl.service.dto.GoalUpdateDto
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.comparables.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull

@SpringBootTest
class GoalServiceTest(
    private val reportRepository: ReportRepository,
    private val goalRepository: GoalRepository,
    private val goalService: GoalService,
): BehaviorSpec({
    given("성과 목표 생성") {
        val report = reportRepository.save(Report(
            title = "title",
            createdBy = "createdBy",
            reportType = ReportType.A
        ))
        When("정상적으로 입력하면") {
            val goalId:Long = goalService.create(report.id, GoalCreateDto(
                sGoal = "전략목표",
                pGoal = "성과목표",
                createdBy = "작성자",
            )
            )
            then("생성됨을 확인") {
                goalId shouldBeGreaterThan 0L
                val goal: Goal? = goalRepository.findByIdOrNull(goalId)
                goal shouldNotBe null
                goal?.sGoal shouldBe "전략목표"
                goal?.pGoal shouldBe "성과목표"
            }
            then("보고서 엔티티에서도 확인가능") {
                report.goal shouldBe null
                val rereport = reportRepository.findByIdOrNull(report.id)
                rereport?.goal!!.sGoal shouldBe "전략목표"
                rereport?.goal!!.pGoal shouldBe "성과목표"
            }
        }
        When("보고서가 없으면") {
            then("없다고 오류 발생") {
                shouldThrow<ReportNotFoundException> { goalService.create(99L, GoalCreateDto(
                    sGoal = "전략목표",
                    pGoal = "성과목표",
                    createdBy = "작성자",
                ))}
            }
        }
    }
    given("성과 목표 수정") {
        val report = reportRepository.save(Report(
            title = "title",
            createdBy = "createdBy",
            reportType = ReportType.A
        ))
        val goalId:Long = goalService.create(report.id, GoalCreateDto(
            sGoal = "전략목표",
            pGoal = "성과목표",
            createdBy = "작성자",
        ))
        When("성과 목표를 정상적으로 수정하면") {
            val updatedGoalId:Long = goalService.update(goalId, GoalUpdateDto(
                sGoal = "수정된 전략목표",
                pGoal = "수정된 성과목표",
                updatedBy = "수정자"
            )
            )
            then("정상적으로 수정됨") {
                updatedGoalId shouldBeGreaterThan 0L
                val updatedGoal: Goal? = goalRepository.findByIdOrNull(updatedGoalId)
                updatedGoal shouldNotBe null
                updatedGoal?.sGoal shouldBe "수정된 전략목표"
                updatedGoal?.pGoal shouldBe "수정된 성과목표"
                updatedGoal?.updatedBy shouldBe "수정자"
            }
        }
        When("해당 성과 목표가 없으면") {
            then("성과 목표를 찾을수 없다고 오류 발생") {
                shouldThrow<GoalNotFoundException> { goalService.update(999L, GoalUpdateDto(
                    sGoal = "수정된 전략목표",
                    pGoal = "수정된 성과목표",
                    updatedBy = "수정자"
                ))}
            }
        }
    }
    given("성과목표 삭제") {
        val report = reportRepository.save(Report(
            title = "title",
            createdBy = "createdBy",
            reportType = ReportType.A
        ))
        val goal: Goal = goalRepository.save(Goal(
            sGoal = "전략목표1",
            pGoal = "성과목표1",
            createdBy = "spark",
            report = report
        ))
        When("정상적으로 삭제") {
            val deletedGoalId = goalService.delete(goal.id, "spark")
            then("삭제 됨을 확인") {
                deletedGoalId shouldBe goal.id
                val deletedGoal: Goal? = goalRepository.findByIdOrNull(goal.id)
                deletedGoal shouldBe null
            }
        }
        When("삭제 대상이 없으면") {
            then("해당하는 성과목표가 없다고 오류") {
                shouldThrow<GoalNotFoundException> { goalService.delete(999L, "spark") }
            }
        }
    }
})