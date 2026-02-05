package com.amanuelht.taxcalc_api

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus

/**
 * Integration test for the TaxController.
 * Verifies that the API correctly calculates tax based on income input.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaxControllerTests(@Autowired val restTemplate: TestRestTemplate) {

    @Test
    @DisplayName("✅ Should calculate correct tax for a given income and return HTTP 200")
    fun `calculateTax should return correct tax amount`() {
        val income = 100000.0
        val expectedTax = income * 0.27

        // Send GET request to the API endpoint
        val response = restTemplate.getForEntity("/api/tax/$income", Map::class.java)

        println("🔹 HTTP Status: ${response.statusCode}")
        println("🔹 Body: ${response.body}")

        // Validate response body
        val body = response.body ?: error("Response body is null")
        val incomeValue = (body["income"] as Number).toDouble()
        val taxValue = (body["tax"] as Number).toDouble()

        println("🔹 incomeValue=$incomeValue, taxValue=$taxValue, expectedTax=$expectedTax")

        // Assertions
        assertEquals(HttpStatus.OK, response.statusCode, "HTTP status should be 200 OK")
        assertEquals(income, incomeValue, 0.0001, "Returned income should match input")
        assertEquals(expectedTax, taxValue, 0.0001, "Tax should be correctly calculated")
    }
}
