package com.example.skpapikotl.controller.dto

import com.example.skpapikotl.service.dto.StageUpdateDto

data class StagePutRequest(
    val stage: Long,
    val startAt: String,
    val endAt: String,
    val content: String,
    val updatedBy: String,
)

fun StagePutRequest.toDto() = StageUpdateDto(
    stage = stage,
    startAt = startAt,
    endAt = endAt,
    content = content,
    updatedBy = updatedBy,
)