package com.santa.hamburgueria.request

import com.santa.hamburgueria.model.enuns.Carne
import com.santa.hamburgueria.model.enuns.Queijo
import com.santa.hamburgueria.model.enuns.Salada
import com.santa.hamburgueria.model.enuns.TipoDePao

data class HamburguerResponse(

    val id: Int,
    val tipoDePao: TipoDePao,
    val carne: Carne,
    val queijo: Queijo,
    val salada: Salada
){
    constructor(tipoDePao: TipoDePao, carne: Carne, queijo: Queijo, salada: Salada):
            this(0,tipoDePao, carne, queijo, salada)
}
