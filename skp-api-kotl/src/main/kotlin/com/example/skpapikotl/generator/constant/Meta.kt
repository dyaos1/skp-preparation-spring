package com.example.skpapikotl.generator.constant

import com.example.skpapikotl.domain.Report

data class Meta(
    val title: String,
    val reportType: String,
    val createdBy: String,
    val createdAt: String,
    val updatedAt: String,
)

fun Report.toMeta() = Meta(
    title = title,
    reportType = reportType.toString(),
    createdBy = createdBy,
    createdAt = createdAt.toString(),
    updatedAt = updatedAt.toString(),
)