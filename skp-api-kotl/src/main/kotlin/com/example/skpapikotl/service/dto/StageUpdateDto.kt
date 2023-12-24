package com.example.skpapikotl.service.dto

data class StageUpdateDto(
    val stage: Long,
    val startAt: String,
    val endAt: String,
    val updatedBy: String,
    val content: String,
)
