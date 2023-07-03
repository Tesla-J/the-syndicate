package com.thesyndicate.entity

import jakarta.persistence.*

@Entity
@Table(name = "Transaction")
data class Transaction(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long?,
                       @ManyToOne @JoinColumn(name = "idOriginWallet", referencedColumnName = "address") val idOriginWallet: Wallet,
                       @ManyToOne @JoinColumn(name = "idDestinationWallet", referencedColumnName = "address") val idDestinationWallet: Wallet,
                       @Column(nullable = false) val amount: Double,
                       val description: String?,
                       @Column(nullable = false) val date: String)
