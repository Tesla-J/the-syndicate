package com.evilgemini.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "User")
data class User(
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	val id: Int = 0,
	val username: String = "",
	val password: String = "",
	val isEmployee: Boolean = false
)