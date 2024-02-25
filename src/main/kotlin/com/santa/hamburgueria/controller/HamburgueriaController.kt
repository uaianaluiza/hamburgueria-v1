package com.santa.hamburgueria.controller

import com.santa.hamburgueria.model.Hamburguer
import com.santa.hamburgueria.request.HamburguerRequest
import com.santa.hamburgueria.service.HamburguerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/hamburguer/")
class HamburgueriaController(
    private val service: HamburguerService
){

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun criarHamburguer(@RequestBody request: HamburguerRequest): Hamburguer {
        return service.criarHamburguer(request)
    }

    @GetMapping("listarTodos/")
    @ResponseStatus(HttpStatus.OK)
    fun listarHamburguer() : Iterable<Hamburguer>{
        return service.listarHamburguer()
    }

    @GetMapping("listar/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun buscarHamburguerPorId(@PathVariable id: Int): Hamburguer{
        return service.buscarHamburguerPeloId(id)
    }

    @PutMapping("alterar/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun alterarHamburguer(@PathVariable id: Int,
                          @RequestBody escolha: HamburguerRequest):Hamburguer{
        return service.alterarHamburguer(id,escolha)
    }

    @DeleteMapping("deletar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletarHamburguer(@PathVariable id: Int){
        return service.deletarHamburguer(id)
    }
    @DeleteMapping("deletarTodos/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletarHamburgueres(){
        return service.deletarHamburgueres()
    }
}