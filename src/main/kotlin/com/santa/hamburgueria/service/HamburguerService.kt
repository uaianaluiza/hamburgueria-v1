package com.santa.hamburgueria.service

import com.santa.hamburgueria.exceptions.HamburguerException
import com.santa.hamburgueria.model.enuns.Carne
import com.santa.hamburgueria.model.enuns.Queijo
import com.santa.hamburgueria.model.enuns.Salada
import com.santa.hamburgueria.model.enuns.TipoDePao
import com.santa.hamburgueria.model.Hamburguer
import com.santa.hamburgueria.repository.HamburgueriaRepository
import com.santa.hamburgueria.request.HamburguerRequest
import org.springframework.stereotype.Service

@Service
class HamburguerService(private val repository: HamburgueriaRepository) {

    fun criarHamburguer(request: HamburguerRequest): Hamburguer {
        val tipoDePao = escolherPao(request.tipoDePao)
        val pontoDaCarne = escolherCarne(request.carne)
        val queijo = escolherQueijo(request.queijo)
        val salada = escolherSalada(request.salada)

        return repository.save(Hamburguer(tipoDePao, pontoDaCarne, queijo, salada))
    }

    fun listarHamburguer(): Iterable<Hamburguer> {
        return repository.findAll()
    }

    fun buscarHamburguerPorId(id: Int): Hamburguer {
        val optionalHamburger = repository.findById(id)

        if (optionalHamburger.isPresent) {
            return optionalHamburger.get()
        } else {
            throw HamburguerException("Hamburguer não encontrado com o id: $id")
        }
    }

    fun alterarHamburguer(id: Int, escolha: HamburguerRequest): Hamburguer {
        val hamburguer = buscarHamburguerPorId(id)

        hamburguer.tipoDePao = escolherPao(escolha.tipoDePao)
        hamburguer.carne = escolherCarne(escolha.carne)
        hamburguer.queijo = escolherQueijo(escolha.queijo)
        hamburguer.salada = escolherSalada(escolha.salada)

     return repository.save(hamburguer)
    }


    fun deletarHamburguer(id: Int) {
        val hamburguer = buscarHamburguerPorId(id)
        return repository.deleteById(hamburguer.id)
    }

    fun deletarHamburgueres() {
        repository.deleteAll()
    }

    private fun escolherPao(escolha: Int): TipoDePao {

        val tipoDePao = when (escolha) {
            1 -> TipoDePao.AUSTRALIANO
            2 -> TipoDePao.BRIOCHE
            3 -> TipoDePao.ARTESANAL
            4 -> TipoDePao.SEM_GLUTEM
            else -> throw HamburguerException("Pão - Opção inválida")
        }

        return tipoDePao
    }

    private fun escolherCarne(escolha: Int): Carne {
        val carne = when (escolha) {
            1 -> Carne.AO_PONTO
            2 -> Carne.BEM_PASSADA
            3 -> Carne.MAL_PASSADA
            4 -> Carne.VEGANA
            else -> throw HamburguerException("Carne - Opção inválida")
        }

        return carne
    }

    private fun escolherQueijo(escolha: Int): Queijo {
        val queijo = when (escolha) {
            1 -> Queijo.CHEDDAR
            2 -> Queijo.MINAS
            3 -> Queijo.MUSSARELA
            4 -> Queijo.ZERO_LACTOSE
            5 -> Queijo.SEM_QUEIJO
            else -> throw HamburguerException("Queijo - Opção inválida")
        }
        return queijo
    }

    private fun escolherSalada(escolha: Int): Salada {
        val salada = when (escolha) {
            1 -> Salada.ALFACE
            2 -> Salada.RÚCULA
            3 -> Salada.TOMATE
            4 -> Salada.SEM_SALADA
            else -> throw HamburguerException("Salada - Opção inválida")
        }

        return salada
    }
}