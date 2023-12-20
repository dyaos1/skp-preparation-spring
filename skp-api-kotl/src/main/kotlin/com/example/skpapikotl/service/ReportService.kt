package com.example.skpapikotl.service

import com.example.skpapikotl.constant.ReportType
import com.example.skpapikotl.domain.Report
import com.example.skpapikotl.exception.ReportNotFoundException
import com.example.skpapikotl.repository.ReportRepository
import com.example.skpapikotl.service.dto.ReportCreateDto
import com.example.skpapikotl.service.dto.ReportSearchDto
import com.example.skpapikotl.service.dto.ReportUpdateDto
import com.example.skpapikotl.service.dto.toEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ReportService(private val reportRepository: ReportRepository) {
    @Transactional
    fun create(
        reportCreateDto: ReportCreateDto
    ): Long {
        return reportRepository.save(reportCreateDto.toEntity()).id
    }

    @Transactional
    fun update(reportId: Long, updater: ReportUpdateDto): Long {
        val targetReport: Report = reportRepository.findByIdOrNull(reportId) ?: throw ReportNotFoundException()
        targetReport.update(updater)
        return targetReport.id
    }

    @Transactional
    fun delete(reportId: Long, deleter: String): Long {
        val targetReport: Report = reportRepository.findByIdOrNull(reportId) ?: throw ReportNotFoundException()
        reportRepository.delete(targetReport)
        return targetReport.id
    }

    fun getReport(reportId: Long): Report? {
        return reportRepository.findByIdOrNull(reportId) ?: throw ReportNotFoundException()
    }

    @Transactional
    fun findPageBy(pageRequest: Pageable, reportSearchDto: ReportSearchDto): Page<Report> {
        val reportTypeValue = if (reportSearchDto.reportType != null) ReportType.valueOf(reportSearchDto.reportType!!) else null
        val results:List<Report> = reportRepository.searchReport(
            title = reportSearchDto.title,
            createdBy = reportSearchDto.createdBy,
            reportType = reportTypeValue
        )
        val resultLength = results.size
        return PageImpl(results, pageRequest, resultLength.toLong())
    }
}


