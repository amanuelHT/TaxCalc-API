package com.amanuelht.taxcalc_api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class TaxController(private val taxService: TaxService) {

    @GetMapping("/api/tax/{income}")
    fun calculateTax(@PathVariable income: Double): Map<String, Any> {
        return mapOf(
            "income" to income,
            "tax" to taxService.calculateTax(income)
        )
    }

    @GetMapping("/api/tax/brackets")
    fun getTaxBrackets(): Map<String, Any> {
        return taxService.getTaxBrackets()
    }

    @GetMapping("/api/tax/details/{income}")
    fun getTaxDetails(@PathVariable income: Double): Map<String, Any> {
        return taxService.calculateDetailed(income)
    }

    @GetMapping("/api/tax/validate/{income}")
    fun validateIncome(@PathVariable income: Double): Map<String, Any> {
        val valid = taxService.validateIncome(income)
        return mapOf(
            "income" to income,
            "valid" to valid,
            "message" to if (valid) 
                "Income is valid" 
            else 
                "Income cannot be negative"
        )
    }
}
