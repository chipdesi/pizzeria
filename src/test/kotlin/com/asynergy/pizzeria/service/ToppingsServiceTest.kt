package com.asynergy.pizzeria.service

import com.asynergy.pizzeria.data.CustomerToppings
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.assertEquals


@DataJpaTest
class ToppingsServiceTest {

    lateinit var dataSource: EmbeddedDatabase
    lateinit var toppingsService: ToppingsService

    @BeforeTest
    fun setup() {
        dataSource = EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
            .addScript("classpath:schema.sql")
            .addScript("classpath:test-data.sql")
            .build()
        toppingsService = ToppingsService(JdbcTemplate(dataSource))
    }

    @AfterTest
    fun teardown() {
        dataSource.shutdown()
    }

    @Test
    fun whenGetToppings_returnAllToppingsAndCount() {
        val toppings = toppingsService.getToppings()
        assertEquals(5, toppings.size)

        val toppingCountMap = toppings.associate { it.topping to it.count }
        assertEquals(1, toppingCountMap["anchovies"])
        assertEquals(3, toppingCountMap["pepperoni"])
        assertEquals(1, toppingCountMap["sausage"])
        assertEquals(2, toppingCountMap["green peppers"])
        assertEquals(1, toppingCountMap["bacon"])
    }

    @Test
    fun whenPostToppings_updateNewToppingCount() {
        val customerToppings = CustomerToppings(
            "dave@gmail.com",
            listOf("pepperoni", "pineapple", "bacon"))
        toppingsService.saveToppings(customerToppings)

        val toppings = toppingsService.getToppings()
        assertEquals(6, toppings.size)

        val toppingCountMap = toppings.associate { it.topping to it.count }
        assertEquals(1, toppingCountMap["anchovies"])
        assertEquals(4, toppingCountMap["pepperoni"])
        assertEquals(1, toppingCountMap["sausage"])
        assertEquals(2, toppingCountMap["green peppers"])
        assertEquals(2, toppingCountMap["bacon"])
        assertEquals(1, toppingCountMap["pineapple"])
    }

    @Test
    fun whenPostToppingsCustomerExists_replaceToppings() {
        val customerToppings = CustomerToppings(
            "alice@gmail.com",
            listOf("pineapple")
        )
        toppingsService.saveToppings(customerToppings)

        val toppings = toppingsService.getToppings()
        assertEquals(4, toppings.size)

        val toppingCountMap = toppings.associate { it.topping to it.count }
        assertEquals(2, toppingCountMap["pepperoni"])
        assertEquals(2, toppingCountMap["green peppers"])
        assertEquals(1, toppingCountMap["bacon"])
        assertEquals(1, toppingCountMap["pineapple"])
    }
}