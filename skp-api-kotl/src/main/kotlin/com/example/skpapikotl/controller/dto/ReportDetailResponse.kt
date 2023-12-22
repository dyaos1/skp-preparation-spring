package com.example.skpapikotl.controller.dto

import com.example.skpapikotl.domain.Report

data class ReportDetailResponse(
    val title: String,
    val createdBy: String,
    val reportType: String,
    val description: String?,
    val createdAt: String,
    val updatedAt: String?,
)

fun Report.toResponseDto() = ReportDetailResponse(
    title = title,
    createdBy = createdBy,
    reportType = reportType.toString(),
    description = description,
    createdAt = createdAt.toString(),
    updatedAt = updatedAt?.toString(),
)
