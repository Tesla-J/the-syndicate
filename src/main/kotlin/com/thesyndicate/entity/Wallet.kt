package com.thesyndicate.entity

import jakarta.persistence.*
import java.security.MessageDigest
import kotlin.random.Random

@Entity
@Table(name = "Wallet")
data class Wallet(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long?,
                  @Column(unique = true) val address: String,
                  val balance: Double,
                  @ManyToOne @JoinColumn(name = "owner", referencedColumnName = "id") val owner: User?)

fun generateHash(): String{
    val algorithm = "SHA-512"
    val data = Random.nextInt(0,1001).toString()
    val maxSize = 35 // a bitcoin wallet address is 26-35 long

    val messageDigest = MessageDigest.getInstance(algorithm)
    val hashBytes = messageDigest.digest(data.toByteArray())
    val truncatedHashBytes = hashBytes.copyOf(maxSize)

    return truncatedHashBytes.joinToString("") { "%02x".format(it) }
}