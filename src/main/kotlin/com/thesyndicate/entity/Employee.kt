package com.thesyndicate.entity

import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDate

data class Employee(
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    val id: Int?,
    @Column(nullable = false)
    val name: String,
    @Column(nullable = false)
    val bi: String,
    @Column(nullable = false, unique = true)
    val phoneNumber: String,
    @Column(nullable = false)
    val email: String,
    @Column(nullable = false)
    val birthDate: LocalDate,
    @Column(nullable = false)
    val userId: User
)
