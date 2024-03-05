package com.santa.hamburgueria

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HamburgerApplication

fun main(args: Array<String>) {
    runApplication<HamburgerApplication>(*args)
}
