package com.example.skpapikotl.controller.dto

data class StagePutRequest(
    val stage: Long,
    val startAt: String,
    val endAt: String,
    val content: String,
    val updatedBy: String,
)
