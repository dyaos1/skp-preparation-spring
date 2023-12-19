package com.example.skpapikotl.domain

import com.example.skpapikotl.constant.ReportType
import com.example.skpapikotl.service.dto.ReportUpdateDto
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Report(
    title: String,
    createdBy: String,
    reportType: ReportType,
    description: String? = null,
) : BaseEntity(createdBy) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    var title = title

    var reportType = reportType

    var description = description

    fun update(updater: ReportUpdateDto) {
        this.title = updater.title
        this.reportType = ReportType.valueOf(updater.reportType)
        this.description = updater.description
        super.update(updater.updatedBy)
    }
}