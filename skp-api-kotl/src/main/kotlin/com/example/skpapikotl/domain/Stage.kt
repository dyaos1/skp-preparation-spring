package com.example.skpapikotl.domain

import com.example.skpapikotl.service.dto.StageUpdateDto
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne

@Entity
class Stage(
    stage: Long,
    startAt: String,
    endAt: String,
    createdBy: String,
    content: String,
    report: Report,
): BaseEntity(createdBy) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    var stage: Long = stage
        protected set

    var startAt: String = startAt
        protected set

    var endAt: String = endAt
        protected set

    var content: String = content
        protected set

    @ManyToOne
    var report: Report = report
        protected set

    fun update(updator: StageUpdateDto) {
        this.stage = updator.stage
        this.startAt = updator.startAt
        this.endAt = updator.endAt
        this.content = updator.content
        super.updateMeta(updator.updatedBy)
    }
}