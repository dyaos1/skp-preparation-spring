package com.example.skpapikotl.controller

import com.example.skpapikotl.controller.dto.GoalPostRequest
import com.example.skpapikotl.controller.dto.GoalPutRequest
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class GoalController {
    @PostMapping("/report/{reportId}/goal")
    fun createGoal(
        @PathVariable reportId: Long,
        @RequestBody goalPostRequest: GoalPostRequest,
    ): Long {
        println(goalPostRequest.sGoal)
        println(goalPostRequest.pGoal)
        return 1L
    }

    @PutMapping("/goal/{goalId}")
    fun updateGoal(
        @PathVariable goalId: Long,
        @RequestBody goalPutRequest: GoalPutRequest,
    ): Long {
        println(goalId)
        println(goalPutRequest.sGoal)
        println(goalPutRequest.pGoal)
        return 1L
    }

    @DeleteMapping("/goal/{goalId}")
    fun deleteGoal(
        @PathVariable goalId: Long,
        @RequestParam createdBy: String,
    ) : Long {
        println(goalId)
        println(createdBy)
        return 1L
    }
}