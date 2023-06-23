package com.thesyndicate.repository

import com.thesyndicate.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
	fun findByUsername(username: String): User
}