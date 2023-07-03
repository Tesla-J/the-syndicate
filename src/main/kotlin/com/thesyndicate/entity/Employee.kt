package com.thesyndicate.entity

import jakarta.persistence.*

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

                    @Column(nullable = false, columnDefinition = "DATE NOT NULL")
                    val birthDate: String,

                    //@Column(unique = true, nullable = false) -> not allowed
                    @ManyToOne
                    @JoinColumn(name = "userId", referencedColumnName = "id")
                    val userId: User?)
