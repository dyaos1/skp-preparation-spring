package com.example.skpapikotl.exception

open class GoalException(message: String) : RuntimeException(message)

class GoalNotFoundException() : GoalException("해당하는 목표를 찾을 수 없습니다")