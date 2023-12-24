package com.example.skpapikotl.exception

open class StageException(message: String) : RuntimeException(message)

class StageNotFoundException(): StageException("해당하는 단계별 성과내용을 찾을 수 없습니다.")
