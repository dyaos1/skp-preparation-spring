package com.example.skpapikotl.service

import com.example.skpapikotl.domain.Goal
import com.example.skpapikotl.domain.Report
import com.example.skpapikotl.exception.GoalNotFoundException
import com.example.skpapikotl.exception.ReportNotFoundException
import com.example.skpapikotl.repository.GoalRepository
import com.example.skpapikotl.repository.ReportRepository
import com.example.skpapikotl.service.dto.GoalCreateDto
import com.example.skpapikotl.service.dto.GoalUpdateDto
import com.example.skpapikotl.service.dto.toEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class GoalService(
    private val reportRepository: ReportRepository,
    private val goalRepository: GoalRepository,
) {
    @Transactional
    fun create(reportId: Long, goalCreateDto: GoalCreateDto): Long {
        val report: Report = reportRepository.findByIdOrNull(reportId) ?: throw ReportNotFoundException()
        return goalRepository.save(goalCreateDto.toEntity(report)).id
    }

    @Transactional
    fun update(goalId: Long, goalUpdateDto: GoalUpdateDto): Long {
        val goal: Goal = goalRepository.findByIdOrNull(goalId) ?: throw GoalNotFoundException()
        return goal.id
    }

    @Transactional
    fun delete(goalId: Long, s: String): Long {
        val goal: Goal = goalRepository.findByIdOrNull(goalId) ?: throw GoalNotFoundException()
        goalRepository.delete(goal)
        return goalId
    }
}


