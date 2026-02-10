package com.amanuelht.taxcalc_api

import org.springframework.stereotype.Service

@Service
class TaxService {

    private val taxRate = 0.27

    fun calculateTax(income: Double): Double {
        return income * taxRate
    }

    fun getTaxBrackets(): Map<String, Any> {
        return mapOf(
            "baseRate" to taxRate,
            "description" to "Flat tax calculation for demonstration purposes"
        )
    }

    fun calculateDetailed(income: Double): Map<String, Any> {
        val tax = calculateTax(income)

        return mapOf(
            "income" to income,
            "taxRate" to taxRate,
            "calculatedTax" to tax,
            "effectiveRatePercent" to taxRate * 100
        )
    }

    fun validateIncome(income: Double): Boolean {
        return income >= 0
    }
}
