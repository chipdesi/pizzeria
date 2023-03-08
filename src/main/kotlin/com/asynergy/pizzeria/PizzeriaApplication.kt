package com.asynergy.pizzeria

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PizzeriaApplication

fun main(args: Array<String>) {
    runApplication<PizzeriaApplication>(*args)
}
