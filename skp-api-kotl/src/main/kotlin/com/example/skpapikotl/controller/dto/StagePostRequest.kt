package com.example.skpapikotl.controller.dto


data class StagePostRequest(
    val stage: Long,
    val startAt: String,
    val endAt: String,
    val content: String,
    val createdBy: String,
)
