package com.example.skpapikotl.generator

import com.example.skpapikotl.generator.dto.GeneratorResponse
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class GeneratorController(
    private val generatorService: GeneratorService
)
{
    @GetMapping("/generator")
    @CrossOrigin("*")
    fun getResult(
        @RequestParam reportId: Long,
        @RequestParam generatedBy: String,
    ): GeneratorResponse {
        generatorService.init(reportId, generatedBy)
        return generatorService.getResult()
    }
}