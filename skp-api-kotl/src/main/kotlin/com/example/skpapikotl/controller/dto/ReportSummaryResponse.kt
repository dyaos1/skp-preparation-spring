package com.example.skpapikotl.controller.dto

import com.example.skpapikotl.domain.Report

data class ReportSummaryResponse(
    val id: Long,
    val title: String,
    val reportType: String,
    val createdBy: String,
    val createdAt: String,
)

fun Report.toSummaryResponse() = ReportSummaryResponse(
    id = id,
    title = title,
    reportType = reportType.toString(),
    createdBy = createdBy,
    createdAt = createdAt.toString(),
)
