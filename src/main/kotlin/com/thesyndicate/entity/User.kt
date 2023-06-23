package com.thesyndicate.entity

import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Id
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Column

@Entity
@Table (name = "Users")
data class User(
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	val id: Long,
	@Column (unique = true)
	val username: String,
	val password: String,
	@Column(nullable = false, columnDefinition = "BOOLEAN NOT NULL DEFAULT FALSE")
	val isEmployee: Boolean = true
)