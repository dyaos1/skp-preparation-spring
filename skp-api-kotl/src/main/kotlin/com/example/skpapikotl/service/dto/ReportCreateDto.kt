package com.example.skpapikotl.service.dto

import com.example.skpapikotl.constant.ReportType
import com.example.skpapikotl.domain.Report

data class ReportCreateDto(
    val title: String,
    val createdBy: String,
    val reportType: String,
    val description: String? = null,
)

fun ReportCreateDto.toEntity() = Report(
    title = title,
    createdBy = createdBy,
    reportType = ReportType.valueOf(reportType),
    description = description
)