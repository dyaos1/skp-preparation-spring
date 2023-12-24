package com.example.skpapikotl.controller

import com.example.skpapikotl.controller.dto.GoalPostRequest
import com.example.skpapikotl.controller.dto.GoalPutRequest
import com.example.skpapikotl.controller.dto.toDto
import com.example.skpapikotl.service.GoalService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class GoalController(
    private val goalService: GoalService
) {
    @PostMapping("/report/{reportId}/goal")
    fun createGoal(
        @PathVariable reportId: Long,
        @RequestBody goalPostRequest: GoalPostRequest,
    ): Long {
        return goalService.create(reportId, goalPostRequest.toDto())
    }

    @PutMapping("/goal/{goalId}")
    fun updateGoal(
        @PathVariable goalId: Long,
        @RequestBody goalPutRequest: GoalPutRequest,
    ): Long {
        return goalService.update(goalId, goalPutRequest.toDto())
    }

    @DeleteMapping("/goal/{goalId}")
    fun deleteGoal(
        @PathVariable goalId: Long,
        @RequestParam createdBy: String,
    ) : Long {
        return goalService.delete(goalId, createdBy)
    }
}


