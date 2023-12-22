package com.example.skpapikotl.repository

import com.example.skpapikotl.domain.Goal
import org.springframework.data.jpa.repository.JpaRepository

interface GoalRepository: JpaRepository<Goal, Long> {
}