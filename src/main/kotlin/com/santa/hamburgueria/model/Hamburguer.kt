package com.santa.hamburgueria.model

import com.santa.hamburgueria.model.enuns.TipoDePao
import com.santa.hamburgueria.model.enuns.Carne
import com.santa.hamburgueria.model.enuns.Queijo
import com.santa.hamburgueria.model.enuns.Salada
import jakarta.persistence.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Entity
@Table(name="hamburguer")
class Hamburguer(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    val id: Int = 0,

    @NotNull(message = "Pao não pode ser nulo")
    @NotEmpty(message = "Pao não pode ser vazio")
    @Column(name = "tipoDePao")
    @Enumerated(EnumType.STRING)
    var tipoDePao: TipoDePao,

    @NotNull(message = "Carne não pode ser nulo")
    @NotEmpty(message = "Carne não pode ser vazio")
    @Column(name = "carne")
    @Enumerated(EnumType.STRING)
    var carne: Carne,

    @NotNull(message = "Queijo não pode ser nulo")
    @NotEmpty(message = "Queijo não pode ser vazio")
    @Column(name = "queijo")
    @Enumerated(EnumType.STRING)
    var queijo: Queijo,

    @NotNull(message = "Salada não pode ser nulo")
    @NotEmpty(message = "Salada não pode ser vazio")
    @Column(name = "salada")
    @Enumerated(EnumType.STRING)
    var salada: Salada

){
    constructor(tipoDePao: TipoDePao,carne: Carne,queijo: Queijo,salada: Salada):
            this(0,tipoDePao, carne, queijo, salada)
}
