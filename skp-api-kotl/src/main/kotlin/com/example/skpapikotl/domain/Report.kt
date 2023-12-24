package com.example.skpapikotl.domain

import com.example.skpapikotl.constant.ReportType
import com.example.skpapikotl.service.dto.ReportUpdateDto
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.GeneratedValue
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne


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
        protected set

    var reportType = reportType
        protected set

    var description = description
        protected set

    @OneToOne(mappedBy = "report", orphanRemoval = true, cascade = [CascadeType.ALL])
    var goal: Goal? = null
        protected set

    @OneToMany(mappedBy = "report", orphanRemoval = true, cascade = [CascadeType.ALL])
    var stages: MutableList<Stage> = mutableListOf()

    fun update(updater: ReportUpdateDto) {
        this.title = updater.title
        this.reportType = ReportType.valueOf(updater.reportType)
        this.description = updater.description
        super.updateMeta(updater.updatedBy)
    }

}