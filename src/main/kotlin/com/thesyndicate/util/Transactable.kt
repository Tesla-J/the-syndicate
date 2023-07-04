package com.thesyndicate.util

import com.thesyndicate.entity.Wallet

interface Transactable {
    fun transact(source: Wallet, destination: Wallet, amount: Double, description: String)
}