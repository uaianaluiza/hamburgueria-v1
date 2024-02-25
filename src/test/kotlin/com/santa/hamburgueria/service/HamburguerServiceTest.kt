package com.santa.hamburgueria.service

import com.santa.hamburgueria.model.Hamburguer
import com.santa.hamburgueria.model.enuns.Carne
import com.santa.hamburgueria.model.enuns.Queijo
import com.santa.hamburgueria.model.enuns.Salada
import com.santa.hamburgueria.model.enuns.TipoDePao
import com.santa.hamburgueria.repository.HamburgueriaRepository
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class HamburguerServiceTest {

    @Mock
    private lateinit var repository : HamburgueriaRepository

    @InjectMocks
    private lateinit var service : HamburguerService

    val hamburguerTeste = Hamburguer(14,TipoDePao.AUSTRALIANO,Carne.BEM_PASSADA,Queijo.MUSSARELA,Salada.ALFACE)



}