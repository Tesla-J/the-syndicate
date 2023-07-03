package com.thesyndicate.entity

import jakarta.persistence.*

@Entity
@Table(name = "Corpses")
data class Corpses(@Id
                   @GeneratedValue(strategy = GenerationType.IDENTITY)
                   val id: Long?,

                   @Column(unique = true, nullable = false)
                   val name: String?,

                   @Column(unique = true)
                   val bi: String?,

                   @Column(columnDefinition = "DATE NOT NULL")
                   val birthDate: String?,

                   @Column(columnDefinition = "DATE NOT NULL")
                   val deceaseDate: String,

                   @ManyToOne
                   @JoinColumn(name = "relatoryId", referencedColumnName = "id")
                   val relatoryId: Relatories)
