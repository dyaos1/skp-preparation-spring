package com.example.skpapikotl.controller

import com.example.skpapikotl.controller.dto.StagePostRequest
import com.example.skpapikotl.controller.dto.StagePutRequest
import com.example.skpapikotl.controller.dto.toDto
import com.example.skpapikotl.repository.StageRepository
import com.example.skpapikotl.service.StageService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class StageController(
    private val stageService: StageService,
) {
    @PostMapping("/report/{reportId}/stage")
    fun createStage(
        @PathVariable reportId: Long,
        @RequestBody stagePostRequest: StagePostRequest,
    ): Long {
        return stageService.create(reportId, stagePostRequest.toDto())
    }

    @PutMapping("/stage/{stageId}")
    fun updateStage(
        @PathVariable stageId: Long,
        @RequestBody stagePutRequest: StagePutRequest,
    ): Long {
        return stageService.update(stageId, stagePutRequest.toDto())
    }

    @DeleteMapping("/stage/{stageId}")
    fun deleteStage(
        @PathVariable stageId: Long,
        @RequestParam createdBy: String,
    ) : Long {
        return stageService.delete(stageId, createdBy)
    }
}