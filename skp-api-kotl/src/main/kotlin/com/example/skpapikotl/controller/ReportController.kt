package com.example.skpapikotl.controller

import com.example.skpapikotl.controller.dto.*
import com.example.skpapikotl.service.ReportService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class ReportController(
    private val reportService: ReportService
) {
    @PostMapping("/report")
    fun createReport(
        @RequestBody reportPostRequest: ReportPostRequest
    ): Long {
        return reportService.create(reportPostRequest.toDto())
    }

    @PutMapping("/report/{id}")
    fun updateReport(
        @PathVariable id: Long,
        @RequestBody reportPutRequest: ReportPutRequest
    ): Long {
        return reportService.update(id, reportPutRequest.toDto())
    }

    @DeleteMapping("/report/{id}")
    fun deleteReport(
        @PathVariable id: Long,
        @RequestParam createdBy: String,
    ): Long {
        return reportService.delete(id, createdBy)
    }

    @GetMapping("/report/{id}")
    fun getReport(
        @PathVariable id: Long
    ): ReportDetailResponse {
        return reportService.getReport(id)
    }

    @GetMapping("/report")
    fun getReports(
        pageable: Pageable,
        reportSearchRequest: ReportSearchRequest
    ): Page<ReportSummaryResponse> {
        return reportService.findPageBy(pageable, reportSearchRequest.toDto())
    }
}