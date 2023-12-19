package com.example.skpapikotl.controller

import com.example.skpapikotl.controller.dto.ReportPostRequest
import com.example.skpapikotl.controller.dto.ReportPutRequest
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class ReportController {
    @PostMapping("/report")
    fun createReport(
        @RequestBody reportPostRequest: ReportPostRequest
    ): Long {
        println(reportPostRequest.title)
        println(reportPostRequest.reportType)
        println(reportPostRequest.createdBy)
        println(reportPostRequest.description)
        return 1L
    }

    @PutMapping("/report/{id}")
    fun updateReport(
        @PathVariable id: Long,
        @RequestBody reportPutRequest: ReportPutRequest
    ): Long {
        println("id: $id")
        println(reportPutRequest.title)
        println(reportPutRequest.reportType)
        println(reportPutRequest.updatedBy)
        println(reportPutRequest.description)
        return 1L
    }

    @DeleteMapping("/report/{id}")
    fun deleteReport(
        @PathVariable id: Long,
        @RequestParam createdBy: String,
    ): Long {
        println(id)
        println(createdBy)
        return 1L
    }

    @GetMapping("/report/{id}")
    fun getReport(
        @PathVariable id: Long
    ): Long {
        println(id)
        return 1L
    }

    @GetMapping("/report")
    fun getReports(
    ): Long {
        return 1L
    }
}