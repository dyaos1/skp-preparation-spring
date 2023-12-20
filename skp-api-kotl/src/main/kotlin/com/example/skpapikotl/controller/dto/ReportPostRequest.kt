package com.example.skpapikotl.controller.dto

data class ReportPostRequest(
    val title: String,
    val createdBy: String,
    val reportType: String,
    val description: String? = null,
)
