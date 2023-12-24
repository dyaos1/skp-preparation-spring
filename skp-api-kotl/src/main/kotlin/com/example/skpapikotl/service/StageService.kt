package com.example.skpapikotl.service

import com.example.skpapikotl.domain.Stage
import com.example.skpapikotl.exception.ReportNotFoundException
import com.example.skpapikotl.exception.StageNotFoundException
import com.example.skpapikotl.repository.ReportRepository
import com.example.skpapikotl.repository.StageRepository
import com.example.skpapikotl.service.dto.StageCreateDto
import com.example.skpapikotl.service.dto.StageUpdateDto
import com.example.skpapikotl.service.dto.toEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class StageService(
    private val reportRepository: ReportRepository,
    private val stageRepository: StageRepository,
) {
    fun create(reportId: Long, stageCreateDto: StageCreateDto): Long {
        val report = reportRepository.findByIdOrNull(reportId) ?: throw ReportNotFoundException()
        return stageRepository.save(stageCreateDto.toEntity(report)).id
    }

    fun update(stageId: Long, stageUpdateDto: StageUpdateDto): Long {
        val targetStage = stageRepository.findByIdOrNull(stageId) ?: throw StageNotFoundException()
        targetStage.update(stageUpdateDto)
        return targetStage.id
    }

    fun delete(stageId: Long, createdBy: String): Long {
        val targetStage: Stage = stageRepository.findByIdOrNull(stageId) ?: throw StageNotFoundException()
        stageRepository.delete(targetStage)
        return targetStage.id
    }
}