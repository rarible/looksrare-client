package com.rarible.looksrare.client.autoconfigure

import com.rarible.looksrare.client.LooksrareClient
import com.rarible.looksrare.client.LooksrareClientV2
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import java.net.URI

@SpringBootTest
@SpringBootConfiguration
@EnableAutoConfiguration
class AutoConfigurationTest {
    @Autowired
    private lateinit var properties: LooksrareClientProperties

    @Autowired
    private lateinit var looksrareClient: LooksrareClient

    @Autowired
    private lateinit var looksrareClientV2: LooksrareClientV2

    @Test
    fun `test default consumer initialized`() {
        assertThat(properties.apiKey).isEqualTo("test-api-key")
        assertThat(properties.proxy).isEqualTo(URI.create("http://test-test:password@proxy.com:123"))
        assertNotEquals(looksrareClient, null)
        assertNotEquals(looksrareClientV2, null)
    }
}
