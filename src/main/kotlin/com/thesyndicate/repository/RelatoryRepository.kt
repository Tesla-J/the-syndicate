package com.thesyndicate.repository

import com.thesyndicate.entity.Relatories
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RelatoryRepository : JpaRepository<Relatories, Long> {
}