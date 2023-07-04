package com.thesyndicate.repository

import com.thesyndicate.entity.Product
import com.thesyndicate.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, Long> {
    fun findByIdSeller(idSeller: User) : List<Product>
}