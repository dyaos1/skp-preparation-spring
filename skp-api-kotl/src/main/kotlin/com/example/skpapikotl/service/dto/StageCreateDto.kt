package com.example.skpapikotl.service.dto

import com.example.skpapikotl.domain.Report
import com.example.skpapikotl.domain.Stage

data class StageCreateDto(
    val startAt: String,
    val endAt: String,
    val createdBy: String,
    val content: String,
)

fun StageCreateDto.toEntity(report: Report) = Stage(
    stage = (report.stages.size + 1).toLong(),
    startAt = startAt,
    endAt = endAt,
    createdBy = createdBy,
    content = content,
    report = report
)