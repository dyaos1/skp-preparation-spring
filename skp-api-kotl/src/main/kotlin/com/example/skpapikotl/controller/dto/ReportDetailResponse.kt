package com.example.skpapikotl.controller.dto

import com.example.skpapikotl.domain.Report
import com.example.skpapikotl.domain.Stage

data class ReportDetailResponse(
    val title: String,
    val createdBy: String,
    val reportType: String,
    val description: String? = null,
    val createdAt: String,
    val updatedAt: String? = null,
    val pGoal: String? = null,
    val sGoal: String? = null,
    val stages: List<StageDetailResponse> = emptyList()
)

fun Report.toResponseDto() = ReportDetailResponse(
    title = title,
    createdBy = createdBy,
    reportType = reportType.toString(),
    description = description,
    createdAt = createdAt.toString(),
    updatedAt = updatedAt?.toString(),
    pGoal = goal?.pGoal,
    sGoal = goal?.sGoal,
    stages = stages.map { it.toResponse() },
)
