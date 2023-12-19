package com.example.skpapikotl.domain

import com.example.skpapikotl.constant.ReportType
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

    val title = title

    val reportType = reportType

    val description = description
}