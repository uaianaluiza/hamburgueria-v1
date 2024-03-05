package com.santa.hamburgueria.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.santa.hamburgueria.model.Hamburguer
import com.santa.hamburgueria.model.enuns.Carne
import com.santa.hamburgueria.model.enuns.Queijo
import com.santa.hamburgueria.model.enuns.Salada
import com.santa.hamburgueria.model.enuns.TipoDePao
import com.santa.hamburgueria.request.HamburguerRequest
import com.santa.hamburgueria.service.HamburguerService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@ExtendWith(MockitoExtension::class)
class HamburguerControllerTest {

    @Mock
    private lateinit var service: HamburguerService

    @InjectMocks
    private lateinit var controller: HamburgueriaController

    private val hamburguerTeste = Hamburguer(14, TipoDePao.AUSTRALIANO, Carne.BEM_PASSADA, Queijo.MUSSARELA, Salada.ALFACE)
    private val hamburguerTeste2 = Hamburguer(15, TipoDePao.SEM_GLUTEM, Carne.MAL_PASSADA, Queijo.ZERO_LACTOSE, Salada.ALFACE)
    private val hamburguerList = listOf(hamburguerTeste, hamburguerTeste2)
    private val request = HamburguerRequest(1, 2, 3, 1)

    @Test
    fun `teste criar`() {

        val mockMvc = MockMvcBuilders.standaloneSetup(controller).build()

        `when`(service.criarHamburguer(request)).thenReturn(hamburguerTeste)

        mockMvc.perform(
            MockMvcRequestBuilders.post("/hamburguer/").contentType(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsBytes(request))
        ).andExpect(MockMvcResultMatchers.status().isCreated)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
    }

    @Test
    fun `teste listar`() {

        val mockMvc = MockMvcBuilders.standaloneSetup(controller).build()

        `when`(service.listarHamburguer()).thenReturn(hamburguerList)

        mockMvc.perform(
            MockMvcRequestBuilders.get("/hamburguer/listarTodos/")
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.content().json(ObjectMapper().writeValueAsString(hamburguerList)))
    }

    @Test
    fun `teste listar por id`() {

        val mockMvc = MockMvcBuilders.standaloneSetup(controller).build()

        `when`(service.buscarHamburguerPorId(14)).thenReturn(hamburguerTeste)

        mockMvc.perform(
            MockMvcRequestBuilders.get("/hamburguer/listar/${hamburguerTeste.id}")
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.content().json(ObjectMapper().writeValueAsString(hamburguerTeste)))
    }

    @Test
    fun `teste alterar`() {

        val mockMvc = MockMvcBuilders.standaloneSetup(controller).build()

        `when`(service.alterarHamburguer(15,HamburguerRequest(3,2,3,1))).thenReturn(hamburguerTeste2)

        mockMvc.perform(
            MockMvcRequestBuilders.put("/hamburguer/alterar/${hamburguerTeste2.id}")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsBytes(HamburguerRequest(3,2,3,1)))
        )
            .andExpect(MockMvcResultMatchers.status().isAccepted)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.content().json(ObjectMapper().writeValueAsString(hamburguerTeste2)))
    }

    @Test
    fun `teste deletar`() {

        val mockMvc = MockMvcBuilders.standaloneSetup(controller).build()

        doNothing().`when`(service).deletarHamburgueres()

        mockMvc.perform(
            MockMvcRequestBuilders.delete("/hamburguer/deletarTodos/"))

            .andExpect(MockMvcResultMatchers.status().isNoContent)
    }

    @Test
    fun`teste deletar por id`(){
        val mockMvc = MockMvcBuilders.standaloneSetup(controller).build()

        doNothing().`when`(service).deletarHamburguer(hamburguerTeste2.id)

        mockMvc.perform(
            MockMvcRequestBuilders.delete("/hamburguer/deletar/${hamburguerTeste2.id}"))

            .andExpect(MockMvcResultMatchers.status().isNoContent)

    }

}