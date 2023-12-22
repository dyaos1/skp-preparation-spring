package com.example.skpapikotl.service.dto

import com.example.skpapikotl.domain.Goal
import com.example.skpapikotl.domain.Report

data class GoalCreateDto(
    val sGoal: String,
    val pGoal: String,
    val createdBy: String
)

fun GoalCreateDto.toEntity(report: Report) = Goal(
    sGoal = sGoal,
    pGoal = pGoal,
    createdBy = createdBy,
    report = report,
)