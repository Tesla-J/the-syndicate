package com.thesyndicate.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun format(date: LocalDate): String = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))