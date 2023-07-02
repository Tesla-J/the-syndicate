package com.thesyndicate.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "Employees")
data class Employee(@Id
                    @GeneratedValue (strategy = GenerationType.IDENTITY)
                    val id: Long?,

                    @Column(nullable = false, unique = true)
                    val name: String,

                    @Column(nullable = false, unique = true)
                    val bi: String,

                    @Column(nullable = false, unique = true)
                    val phoneNumber: String,

                    @Column(nullable = false, unique = true)
                    val email: String,

                    @Column(nullable = false)
                    val birthDate: LocalDate,

                    @Column(unique = true)
                    @ManyToOne
                    @JoinColumn(name = "userId", referencedColumnName = "id")
                    val userId: User?)
