package com.thesyndicate.repository

import com.thesyndicate.entity.User
import com.thesyndicate.entity.Wallet
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WalletRepository : JpaRepository<Wallet, Long> {
    fun findByOwner(owner: User) : Wallet
}