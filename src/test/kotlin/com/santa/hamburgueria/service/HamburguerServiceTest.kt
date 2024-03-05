package com.santa.hamburgueria.service

import com.santa.hamburgueria.exceptions.HamburguerException
import com.santa.hamburgueria.model.Hamburguer
import com.santa.hamburgueria.model.enuns.Carne
import com.santa.hamburgueria.model.enuns.Queijo
import com.santa.hamburgueria.model.enuns.Salada
import com.santa.hamburgueria.model.enuns.TipoDePao
import com.santa.hamburgueria.repository.HamburgueriaRepository
import com.santa.hamburgueria.request.HamburguerRequest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.api.assertThrows
import org.mockito.ArgumentMatchers.any
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.springframework.boot.test.context.SpringBootTest
import java.util.*
import java.util.stream.Stream

@SpringBootTest
class HamburguerServiceTest {

    @Mock
    private lateinit var repository: HamburgueriaRepository

    @InjectMocks
    private lateinit var service: HamburguerService

    private val hamburguerTeste =
        Hamburguer(14, TipoDePao.AUSTRALIANO, Carne.BEM_PASSADA, Queijo.MUSSARELA, Salada.ALFACE)
    private val hamburguerTeste2 =
        Hamburguer(15, TipoDePao.SEM_GLUTEM, Carne.MAL_PASSADA, Queijo.ZERO_LACTOSE, Salada.ALFACE)
    private val hamburguerList = listOf(hamburguerTeste, hamburguerTeste2)
    private val request = HamburguerRequest(1, 2, 3, 1)

    internal data class TabelaTeste(val hamburguerRequest: HamburguerRequest, val hamburguer: Hamburguer? = null, val expected: String)
    internal data class HamburguerTeste(
        val hamburguerRequest: HamburguerRequest,
        val hamburguer: Hamburguer,
        val name: String
    )

    @Test
    fun `teste criar hamburguer`() {

        `when`(repository.save(any(Hamburguer::class.java))).thenReturn(hamburguerTeste)

        val result = service.criarHamburguer(request)

        assertNotNull(result)

        verify(repository, times(1)).save(any(Hamburguer::class.java))

    }

    @Test
    fun `teste buscar hamburguer por id`() {

        `when`(repository.findById(hamburguerTeste.id)).thenReturn(Optional.of(hamburguerTeste))

        val result = service.buscarHamburguerPorId(hamburguerTeste.id)

        assertEquals(hamburguerTeste, result)

        verify(repository, times(1)).findById(hamburguerTeste.id)

    }

    @Test
    fun `teste listar hamburgueres`() {

        `when`(repository.findAll()).thenReturn(hamburguerList)

        val result = service.listarHamburguer()

        assertEquals(hamburguerList, result)

        verify(repository, times(1)).findAll()

    }

    @Test
    fun `teste alterar hamburguer`() {

        `when`(repository.findById(hamburguerTeste2.id)).thenReturn(Optional.of(hamburguerTeste2))
        `when`(repository.save(hamburguerTeste2)).thenReturn(hamburguerTeste2)

        service.alterarHamburguer(15, request)

        verify(repository, times(1)).save(hamburguerTeste2)
        verify(repository, times(1)).findById(hamburguerTeste2.id)

    }

    @Test
    fun `teste deletar hamburgueres`() {
        doNothing().`when`(repository).deleteAll()

        service.deletarHamburgueres()

        verify(repository, times(1)).deleteAll()
    }

    @Test
    fun `teste deletar hamburguer por id`() {

        `when`(repository.findById(hamburguerTeste2.id)).thenReturn(Optional.of(hamburguerTeste2))

        doNothing().`when`(repository).deleteById(hamburguerTeste2.id)

        service.deletarHamburguer(hamburguerTeste2.id)

        verify(repository, times(1)).deleteById(hamburguerTeste2.id)
        verify(repository, times(1)).findById(hamburguerTeste2.id)
    }

    @TestFactory
    fun dynamicTests(): Stream<DynamicTest> {
        val testeCenarios = listOf(
            TabelaTeste(HamburguerRequest(1, 2, 3, 8), null,"Salada - Opção inválida"),
            TabelaTeste(HamburguerRequest(9, 2, 3, 1),null, "Pão - Opção inválida"),
            TabelaTeste(HamburguerRequest(2, 9, 3, 1),null, "Carne - Opção inválida"),
            TabelaTeste(HamburguerRequest(1, 2, 7, 2), null,"Queijo - Opção inválida"),
        )

        return testeCenarios.map { test ->
            DynamicTest.dynamicTest("Teste para ${test.expected}") {
                val exception = assertThrows<HamburguerException> {
                    service.criarHamburguer(test.hamburguerRequest)
                }
                assertEquals(test.expected, exception.message)
            }
        }.stream()
    }

//        @TestFactory
//    fun dynamicTestsOk(): Stream<DynamicTest> {
//        val testeCenariosOk = listOf(
//            HamburguerTeste(
//                HamburguerRequest(1, 1, 1, 1),
//                Hamburguer(TipoDePao.AUSTRALIANO, Carne.AO_PONTO, Queijo.CHEDDAR, Salada.ALFACE),
//                "teste 1"
//            ),
//            HamburguerTeste(
//                HamburguerRequest(2, 2, 2, 2),
//                Hamburguer(TipoDePao.BRIOCHE, Carne.BEM_PASSADA, Queijo.MINAS, Salada.RÚCULA),
//                "teste 2"
//            ),
//            HamburguerTeste(
//                HamburguerRequest(3, 3, 3, 3),
//                Hamburguer(TipoDePao.ARTESANAL, Carne.MAL_PASSADA, Queijo.MUSSARELA, Salada.TOMATE),
//                "teste 3"
//            ),
//            HamburguerTeste(
//                HamburguerRequest(4, 4, 4, 4),
//                Hamburguer(TipoDePao.SEM_GLUTEM, Carne.VEGANA, Queijo.ZERO_LACTOSE, Salada.SEM_SALADA),
//                "teste 4"
//            ),
//            HamburguerTeste(
//                HamburguerRequest(4, 4, 5, 4),
//                Hamburguer(TipoDePao.SEM_GLUTEM, Carne.VEGANA, Queijo.SEM_QUEIJO, Salada.SEM_SALADA),
//                "teste 5"
//            )
//        )
//        return testeCenariosOk.map { test ->
//            DynamicTest.dynamicTest("${test.name}") {
//                val resultado = service.criarHamburguer(test.hamburguerRequest)
//
//                assertEquals(test.hamburguer, resultado)
//            }
//
//        }.stream()
//
//    }
}
