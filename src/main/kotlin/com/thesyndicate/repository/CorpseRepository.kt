package com.thesyndicate.repository

import com.thesyndicate.entity.Corpses
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CorpseRepository : JpaRepository<Corpses, Long>{
}