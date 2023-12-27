package com.example.skpapikotl.domain

import com.example.skpapikotl.service.dto.GoalUpdateDto
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToOne

@Entity
class Goal(
    sGoal: String,
    pGoal: String,
    createdBy: String,
    report: Report,
) : BaseEntity(createdBy) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    var sGoal: String = sGoal
        protected set

    var pGoal: String = pGoal
        protected set

    @OneToOne(fetch = FetchType.LAZY)
    var report: Report = report
        protected set

    fun update(updator: GoalUpdateDto) {
        this.sGoal = updator.sGoal
        this.pGoal = updator.pGoal
        super.updateMeta(updator.updatedBy)
    }
}