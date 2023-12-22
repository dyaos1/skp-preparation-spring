package com.example.skpapikotl.controller.dto

import com.example.skpapikotl.service.dto.ReportUpdateDto

data class ReportPutRequest(
    val title: String,
    val updatedBy: String,
    val reportType: String,
    val description: String? = null,
)

fun ReportPutRequest.toDto() = ReportUpdateDto(
    title = title,
    updatedBy = updatedBy,
    reportType = reportType,
    description = description,
)