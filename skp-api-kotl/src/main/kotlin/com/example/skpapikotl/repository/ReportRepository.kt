package com.example.skpapikotl.repository

import com.example.skpapikotl.domain.Report
import org.springframework.data.jpa.repository.JpaRepository

interface ReportRepository : JpaRepository<Report, Long> {
}