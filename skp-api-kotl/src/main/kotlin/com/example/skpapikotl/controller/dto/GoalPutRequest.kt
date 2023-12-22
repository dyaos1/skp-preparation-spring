package com.example.skpapikotl.controller.dto

import com.example.skpapikotl.service.dto.GoalUpdateDto

data class GoalPutRequest(
    val sGoal: String,
    val pGoal: String,
    val updatedBy: String,
)

fun GoalPutRequest.toDto(): GoalUpdateDto {
    return GoalUpdateDto(
        sGoal = sGoal,
        pGoal = pGoal,
        updatedBy = updatedBy
    )
}
