package com.example.skpapikotl.service.dto

data class ReportUpdateDto(
    val title: String,
    val updatedBy: String,
    val reportType: String,
    val description: String,
)
