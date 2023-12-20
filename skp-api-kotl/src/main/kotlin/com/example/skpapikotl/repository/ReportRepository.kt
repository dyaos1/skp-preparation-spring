package com.example.skpapikotl.repository

import com.example.skpapikotl.constant.ReportType
import com.example.skpapikotl.domain.Report
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ReportRepository : JpaRepository<Report, Long> {
//    fun findReportsByTitleContainingAndCreatedByContainingAndReportTypeContainingOrReportTypeIsNullOrderByIdDesc(
//        title: String, createdBy: String, reportType: ReportType
//    ): List<Report>

    @Query("SELECT r FROM Report r " +
            "WHERE (r.title LIKE %:title% OR :title IS NULL) " +
            "AND (r.createdBy LIKE %:createdBy% OR :createdBy IS NULL)" +
            "AND (r.reportType = :reportType OR :reportType IS NULL)" +
            "ORDER BY r.id DESC")
    fun searchReport(title: String?, createdBy: String?, reportType: ReportType?): List<Report>
}
