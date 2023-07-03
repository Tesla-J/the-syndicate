package com.thesyndicate.repository

import com.thesyndicate.entity.Employee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EmployeeRepository : JpaRepository<Employee, Long> {
    fun findByName(name : String) : Employee

    fun findByBi(bi: String) : Employee

    fun findByPhoneNumber(phoneNumber : String) : Employee
}