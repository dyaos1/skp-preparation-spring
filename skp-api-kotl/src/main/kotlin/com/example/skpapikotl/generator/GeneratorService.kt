package com.example.skpapikotl.generator

import com.example.skpapikotl.domain.Report
import com.example.skpapikotl.exception.ReportNotFoundException
import com.example.skpapikotl.generator.constant.Field
import com.example.skpapikotl.generator.constant.Table
import com.example.skpapikotl.generator.constant.toMeta
import com.example.skpapikotl.generator.dto.GeneratorResponse
import com.example.skpapikotl.repository.ReportRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.time.LocalDateTime

@Service
class GeneratorService(private val reportRepository: ReportRepository) {
    var report: Report? = null

    var fieldList: MutableList<Field> = mutableListOf()

    var tableList: MutableList<Table> = mutableListOf()

    var generatedBy: String? = null

    var generatedAt: String = LocalDateTime.now().toString()

    fun init(reportId: Long, generatedBy: String) {
        this.report = reportRepository.findByIdOrNull(reportId) ?: throw ReportNotFoundException()
        this.generatedBy = generatedBy
    }

    fun getResult(): GeneratorResponse {
        if (this.report == null) {
            throw GeneratorNotInitiatedException()
        }

        fieldList.addAll(
            listOf(
                Field(key = "{{title}}", value = this.report!!.title ),
                Field(key = "{{sGoal}}" , value = this.report!!.goal?.sGoal ?: ""),
                Field(key = "{{pGoal}}", value = this.report!!.goal?.pGoal ?: ""),
            )
        )

        if (this.report!!.stages.size == 0) {
            throw GeneratorNotCompletedException()
        }

        var stageList:MutableList<String> = mutableListOf()
        this.report!!.stages.map {
            stageList.add(it.stage.toString() + "단계")
            stageList.add(it.startAt + " ~ " + it.endAt)
            stageList.add(it.content)
        }

        tableList.addAll(
            listOf(
                Table(key = "{{stage}}",
                    value = stageList,
                    column = this.report!!.stages.size.toString(),
                    row = "3"
                ),
            ),
        )

        return GeneratorResponse(
            meta = this.report!!.toMeta(),
            fields = this.fieldList,
            tables = this.tableList,
            generatedAt = this.generatedAt,
            generatedBy = this.generatedBy ?: "",
        )
    }
}