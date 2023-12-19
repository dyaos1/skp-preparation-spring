package com.example.skpapikotl.domain

import jakarta.persistence.MappedSuperclass
import java.time.LocalDateTime

@MappedSuperclass
abstract class BaseEntity(
    createdBy: String,
) {
    val createdBy = createdBy
    val createdAt: LocalDateTime = LocalDateTime.now()
    val updatedBy: String? = null
    var updatedAt: LocalDateTime? = null
}