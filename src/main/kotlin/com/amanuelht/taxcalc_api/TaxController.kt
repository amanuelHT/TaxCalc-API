package com.amanuelht.taxcalc_api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class TaxController {

    @GetMapping("/api/tax/{income}")
    fun calculateTax(@PathVariable income: Double): Map<String, Any> {
        val taxRate = 0.27
        val tax = income * taxRate
        return mapOf(
            "income" to income,
            "tax" to tax
        )
    }
}
