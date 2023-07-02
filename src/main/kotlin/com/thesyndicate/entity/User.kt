package com.thesyndicate.entity

import jakarta.persistence.*
import org.mindrot.jbcrypt.BCrypt

@Entity
@Table (name = "Users")
data class User(@Id
				@GeneratedValue (strategy = GenerationType.IDENTITY)
				val id: Long?,
				@Column (unique = true)
				val username: String,
				@Column (nullable = false)
				val password: String,
				@Column(nullable = false, columnDefinition = "BOOLEAN NOT NULL DEFAULT FALSE")
				val isEmployee: Boolean? = true)
fun encryptPassword(plainText: String): String = BCrypt.hashpw(plainText, BCrypt.gensalt())

fun comparePassword(password: String, hash: String): Boolean = BCrypt.checkpw(password, hash)