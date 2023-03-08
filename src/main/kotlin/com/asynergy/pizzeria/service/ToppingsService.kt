package com.asynergy.pizzeria.service

import com.asynergy.pizzeria.data.CustomerToppings
import com.asynergy.pizzeria.data.ToppingCount
import org.springframework.jdbc.core.BatchPreparedStatementSetter
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.sql.PreparedStatement


@Service
class ToppingsService(val db: JdbcTemplate) {
    companion object {
        const val GET_TOPPINGS_QUERY: String =
            "select topping, count(customer_email) from customer_toppings group by topping"
        const val DELETE_TOPPINGS_QUERY: String =
            "delete from customer_toppings where customer_email = ?"
        const val INSERT_TOPPINGS_QUERY: String =
            "insert into customer_toppings (customer_email, topping) values (?, ?)"
    }

    fun getToppings(): List<ToppingCount> = db.query(GET_TOPPINGS_QUERY) { response, _ ->
        ToppingCount(response.getString(1), response.getInt(2))
    }

    @Transactional
    fun saveToppings(customerToppings: CustomerToppings) {
        db.update(DELETE_TOPPINGS_QUERY, customerToppings.customerEmail)
        db.batchUpdate(INSERT_TOPPINGS_QUERY, object : BatchPreparedStatementSetter {
            override fun setValues(ps: PreparedStatement, i: Int) {
                ps.setString(1, customerToppings.customerEmail)
                ps.setString(2, customerToppings.toppings[i])
            }

            override fun getBatchSize() = customerToppings.toppings.size
        })
    }
}