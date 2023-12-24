package com.example.skpapikotl.controller.dto

import com.example.skpapikotl.service.dto.StageCreateDto


data class StagePostRequest(
    val stage: Long?,
    val startAt: String,
    val endAt: String,
    val content: String,
    val createdBy: String,
)

fun StagePostRequest.toDto() = StageCreateDto(
    stage = stage,
    startAt = startAt,
    endAt = endAt,
    createdBy = createdBy,
    content = content,
)