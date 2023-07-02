package com.thesyndicate.repository

import com.thesyndicate.entity.Employee
import org.springframework.data.jpa.repository.JpaRepository

interface EmployeeRepository : JpaRepository<Employee, Long> {
    fun findByUserId(username: String): Employee
}