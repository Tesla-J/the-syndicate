package com.thesyndicate.entity

import jakarta.persistence.*

@Entity
@Table(name = "Relatories")
data class Relatories(@Id
                      @GeneratedValue(strategy = GenerationType.IDENTITY)
                      val id: Long?,

                      @Column(nullable = false)
                      val title: String,

                      @Column(columnDefinition = "TEXT NOT NULL")
                      val content: String,

                      @ManyToOne
                      @JoinColumn(name = "idAuthor", referencedColumnName = "id")
                      val idAuthor: Employee)
