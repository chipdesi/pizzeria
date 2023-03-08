package com.asynergy.pizzeria.controller

import com.asynergy.pizzeria.data.CustomerToppings
import com.asynergy.pizzeria.data.ToppingCount
import com.asynergy.pizzeria.service.ToppingsService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/toppings")
class ToppingsController(val service: ToppingsService) {
    @GetMapping
    fun get(): List<ToppingCount> = service.getToppings()

    @GetMapping("/chip")
    fun getMyFavorite(): List<String> = listOf("pepperoni", "bacon", "sausage")

    @PostMapping
    fun post(@RequestBody toppings: CustomerToppings) = service.saveToppings(toppings)
}