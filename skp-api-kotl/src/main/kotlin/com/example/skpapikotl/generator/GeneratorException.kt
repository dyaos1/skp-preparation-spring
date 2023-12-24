package com.example.skpapikotl.generator

open class GeneratorException(message: String) : RuntimeException(message)

class GeneratorNotInitiatedException() : GeneratorException("생성기가 초기화 되지 않았습니다.")

class GeneratorNotCompletedException() : GeneratorException("보고서가 완성되지 않았습니다.")