package com.amanuelht.taxcalc_api

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaxAdditionalEndpointsTests(@Autowired val client: TestRestTemplate) {

    @Test
    fun `should return tax brackets`() {
        val response = client.getForEntity("/api/tax/brackets", Map::class.java)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(0.27, (response.body!!["baseRate"] as Number).toDouble(), 0.0001)
    }

    @Test
    fun `should return detailed tax info`() {
        val response = client.getForEntity("/api/tax/details/100000", Map::class.java)
        val body = response.body!!

        assertEquals(100000.0, (body["income"] as Number).toDouble())
        assertEquals(27000.0, (body["calculatedTax"] as Number).toDouble())
    }

    @Test
    fun `should validate positive income`() {
        val response = client.getForEntity("/api/tax/validate/5000", Map::class.java)
        assertEquals(true, response.body!!["valid"])
    }

    @Test
    fun `should detect negative income`() {
        val response = client.getForEntity("/api/tax/validate/-1", Map::class.java)
        assertEquals(false, response.body!!["valid"])
    }
}
