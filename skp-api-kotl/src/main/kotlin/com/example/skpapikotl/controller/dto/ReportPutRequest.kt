package com.example.skpapikotl.controller.dto

data class ReportPutRequest(
    val title: String,
    val updatedBy: String,
    val reportType: String,
    val description: String? = null,
)
