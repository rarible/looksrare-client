package com.rarible.looksrare.client.autoconfigure

import com.rarible.looksrare.client.LooksrareClient
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@SpringBootConfiguration
@EnableAutoConfiguration
class AutoConfigurationTest {

    @Autowired
    private lateinit var looksrareClient: LooksrareClient

    @Test
    fun `test default consumer initialized`() {
        assertNotEquals(looksrareClient, null)
    }
}
