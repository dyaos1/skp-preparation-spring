package com.example.skpapikotl.controller.dto

import com.example.skpapikotl.service.dto.GoalCreateDto

data class GoalPostRequest(
    val sGoal: String,
    val pGoal: String,
    val createdBy: String,
)

fun GoalPostRequest.toDto(): GoalCreateDto {
    return GoalCreateDto(
        sGoal = sGoal,
        pGoal = pGoal,
        createdBy = createdBy,
    )
}