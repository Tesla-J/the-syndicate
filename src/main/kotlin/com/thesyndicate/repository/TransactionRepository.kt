package com.thesyndicate.repository

import com.thesyndicate.entity.Transaction
import com.thesyndicate.entity.Wallet
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository : JpaRepository<Transaction, Long> {
    fun findByIdOriginWallet(origin: Wallet): List<Transaction>

    fun findByIdDestinationWallet(destination: Wallet): List<Transaction>
}