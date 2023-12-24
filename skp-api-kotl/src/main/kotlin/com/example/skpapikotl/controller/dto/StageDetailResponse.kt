package com.example.skpapikotl.controller.dto

import com.example.skpapikotl.domain.Stage

data class StageDetailResponse(
    val stage: String,
    val period: String,
    val content: String,
    val createdBy: String,
    val createdAt: String,
    val updatedAt: String?,
)

fun Stage.toResponse() = StageDetailResponse(
    stage = stage.toString() + "단계",
    period = "$startAt~$endAt",
    content = content,
    createdBy = createdBy,
    createdAt = createdAt.toString(),
    updatedAt = updatedAt?.toString(),
)
