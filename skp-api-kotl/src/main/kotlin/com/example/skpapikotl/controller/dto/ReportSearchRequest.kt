package com.example.skpapikotl.controller.dto

import com.example.skpapikotl.service.dto.ReportSearchDto
import org.springframework.web.bind.annotation.RequestParam

data class ReportSearchRequest(
    @RequestParam
    val title: String? = null,

    @RequestParam
    val createdBy: String? = null,

    @RequestParam
    val reportType: String? = null,
)

fun ReportSearchRequest.toDto() = ReportSearchDto(
    title = title,
    createdBy = createdBy,
    reportType = reportType,
)