package com.example.skpapikotl.generator.dto

import com.example.skpapikotl.generator.constant.Field
import com.example.skpapikotl.generator.constant.Meta
import com.example.skpapikotl.generator.constant.Table

data class GeneratorResponse(
    val meta: Meta,
    val fields: List<Field>,
    val tables: List<Table>,
    val generatedAt: String,
    val generatedBy: String,
)
