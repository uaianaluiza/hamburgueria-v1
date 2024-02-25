package com.santa.hamburgueria.service

import com.santa.hamburgueria.model.enuns.Carne
import com.santa.hamburgueria.model.enuns.Queijo
import com.santa.hamburgueria.model.enuns.Salada
import com.santa.hamburgueria.model.enuns.TipoDePao
import com.santa.hamburgueria.model.Hamburguer
import com.santa.hamburgueria.repository.HamburgueriaRepository
import com.santa.hamburgueria.request.HamburguerRequest
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException
import java.lang.RuntimeException

@Service
class HamburguerService(private val repository: HamburgueriaRepository) {

    fun criarHamburguer(request: HamburguerRequest): Hamburguer {
        val tipoDePao = escolhaDoPao(request.tipoDePao)
        val pontoDaCarne = escolhaCarne(request.carne)
        val queijo = escolhaQueijo(request.queijo)
        val salada = escolhaSalada(request.salada)

        return repository.save(Hamburguer(tipoDePao, pontoDaCarne, queijo, salada))
    }

    fun listarHamburguer(): Iterable<Hamburguer> {
        return repository.findAll()
    }

    fun buscarHamburguerPeloId(id: Int): Hamburguer {
        val optionalHamburger = repository.findById(id)

        if (optionalHamburger.isPresent) {
            return optionalHamburger.get()
        } else {
            throw RuntimeException("Hamburguer não encontrado")
        }
    }

    fun alterarHamburguer(id: Int, escolha: HamburguerRequest): Hamburguer {
        val hamburguer = buscarHamburguerPeloId(id)

        hamburguer.tipoDePao = escolhaDoPao(escolha.tipoDePao)
        hamburguer.carne = escolhaCarne(escolha.carne)
        hamburguer.queijo = escolhaQueijo(escolha.queijo)
        hamburguer.salada = escolhaSalada(escolha.salada)

     return repository.save(hamburguer)
    }


    fun deletarHamburguer(id: Int) {
        val hamburguer = buscarHamburguerPeloId(id)
        return repository.deleteById(hamburguer.id)
    }

    fun deletarHamburgueres() {
        repository.deleteAll()
    }

    private fun escolhaDoPao(escolha: Int): TipoDePao {

        val tipoDePao = when (escolha) {
            1 -> TipoDePao.AUSTRALIANO
            2 -> TipoDePao.BRIOCHE
            3 -> TipoDePao.ARTESANAL
            4 -> TipoDePao.SEM_GLUTEM
            else -> throw IllegalArgumentException("Pão - Opção inválida")
        }

        return tipoDePao
    }

    private fun escolhaCarne(escolha: Int): Carne {
        val carne = when (escolha) {
            1 -> Carne.AO_PONTO
            2 -> Carne.BEM_PASSADA
            3 -> Carne.MAL_PASSADA
            4 -> Carne.VEGANA
            else -> throw IllegalArgumentException("Carne - Opção inválida")
        }

        return carne
    }

    private fun escolhaQueijo(escolha: Int): Queijo {
        val queijo = when (escolha) {
            1 -> Queijo.CHEDDAR
            2 -> Queijo.MINAS
            3 -> Queijo.MUSSARELA
            4 -> Queijo.ZERO_LACTOSE
            5 -> Queijo.SEM_QUEIJO
            else -> throw IllegalArgumentException("Queijo - Opção inválida")
        }
        return queijo
    }

    private fun escolhaSalada(escolha: Int): Salada {
        val salada = when (escolha) {
            1 -> Salada.ALFACE
            2 -> Salada.RÚCULA
            3 -> Salada.TOMATE
            4 -> Salada.SEM_SALADA
            else -> throw IllegalArgumentException("Salada - Opção inválida")
        }

        return salada
    }
}