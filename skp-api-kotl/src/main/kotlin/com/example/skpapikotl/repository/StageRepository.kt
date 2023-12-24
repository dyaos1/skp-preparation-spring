package com.example.skpapikotl.repository

import com.example.skpapikotl.domain.Stage
import org.springframework.data.jpa.repository.JpaRepository

interface StageRepository: JpaRepository<Stage, Long> {
}