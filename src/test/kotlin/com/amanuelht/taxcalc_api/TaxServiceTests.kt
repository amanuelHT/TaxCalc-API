package com.amanuelht.taxcalc_api

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TaxServiceTests {

    private val taxService = TaxService()

    @Test
    fun `calculateTax should return 27 percent of income`() {
        val tax = taxService.calculateTax(100000.0)
        assertEquals(27000.0, tax, 0.0001)
    }
}
