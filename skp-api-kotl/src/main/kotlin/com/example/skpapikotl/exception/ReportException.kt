package com.example.skpapikotl.exception

open class ReportException(message: String) : RuntimeException(message)

class ReportNotFoundException() : ReportException("보고서를 찾을 수 없습니다.")


class ReportTypeException() : ReportException("잘못된 보고서 타입 요청입니다.")