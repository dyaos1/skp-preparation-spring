package com.example.skpapikotl.controller.dto

import com.example.skpapikotl.service.dto.ReportCreateDto

data class ReportPostRequest(
    val title: String,
    val createdBy: String,
    val reportType: String,
    val description: String? = null,
)

fun ReportPostRequest.toDto() = ReportCreateDto(
    title = title,
    createdBy = createdBy,
    reportType = reportType,
    description = description
)