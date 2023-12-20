package com.example.skpapikotl.exception

open class ReportException(message: String) : RuntimeException(message)

class ReportNotFoundException() : ReportException("보고서를 찾을 수 없습니다.")