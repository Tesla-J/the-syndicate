package com.thesyndicate.entity

import jakarta.persistence.*

@Entity
@Table(name = "Product")
data class Product(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long?,
                   val name : String,
                   val stock: Int,
                   @ManyToOne @JoinColumn(name = "idSeller", referencedColumnName = "id")val idSeller: User?,
                   val category: String,
                   @Column(nullable = false) val price : Double,
                   @Column(columnDefinition = "TEXT NOT NULL DEFAULT 'NO DESCRIPTION'") val description: String)

fun getProductCategories() : List<String> = listOf("Organs", "Weapons", "Drugs", "Art", "Slaves", "Fake ID", "Fake money")