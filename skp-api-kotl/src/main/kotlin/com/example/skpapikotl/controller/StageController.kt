package com.example.skpapikotl.controller

import com.example.skpapikotl.controller.dto.StagePostRequest
import com.example.skpapikotl.controller.dto.StagePutRequest
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
class StageController {
    @PostMapping("/report/{reportId}/stage")
    fun createStage(
        @PathVariable reportId: Long,
        @RequestBody stagePostRequest: StagePostRequest,
    ): Long {
        println(stagePostRequest.stage)
        println(stagePostRequest.startAt)
        println(stagePostRequest.endAt)
        println(stagePostRequest.content)
        return 1L
    }

    @PutMapping("/stage/{stageId}")
    fun updateStage(
        @PathVariable stageId: Long,
        @RequestBody stagePutRequest: StagePutRequest,
    ): Long {
        println(stagePutRequest.stage)
        println(stagePutRequest.startAt)
        println(stagePutRequest.endAt)
        println(stagePutRequest.content)
        return 1L
    }

    @DeleteMapping("/stage/{stageId}")
    fun deleteStage(
        @PathVariable stageId: Long,
        @RequestParam createdBy: String,
    ) : Long {
        println(stageId)
        println(createdBy)
        return 1L
    }
}