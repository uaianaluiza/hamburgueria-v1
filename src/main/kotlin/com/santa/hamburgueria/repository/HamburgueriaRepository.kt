package com.santa.hamburgueria.repository

import com.santa.hamburgueria.model.Hamburguer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface HamburgueriaRepository : JpaRepository<Hamburguer,Int>{
}