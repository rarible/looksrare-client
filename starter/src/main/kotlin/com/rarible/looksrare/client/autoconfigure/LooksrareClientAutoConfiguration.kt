package com.rarible.looksrare.client.autoconfigure

import com.rarible.looksrare.client.LooksrareClient
import com.rarible.looksrare.client.LooksrareClientImpl
import com.rarible.looksrare.client.agent.UserAgentProvider
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import java.net.URI

@EnableConfigurationProperties(LooksrareClientProperties::class)
class LooksrareClientAutoConfiguration(
    private val properties: LooksrareClientProperties,
    private val userAgentProvider: UserAgentProvider
) {
    @Bean
    @ConditionalOnMissingBean(LooksrareClient::class)
    fun looksrareClient(): LooksrareClient {
        return LooksrareClientImpl(
            endpoint = properties.endpoint ?: if (properties.testnet) TESTNET_LOOKSRARE_ENDPOINT else LOOKSRARE_ENDPOINT,
            apiKey = properties.apiKey,
            userAgentProvider = if (properties.changeUserAgent) userAgentProvider else null,
            proxy = properties.proxy,
            logRawJson = properties.logRawJson
        )
    }

    private companion object {
        val LOOKSRARE_ENDPOINT: URI = URI.create("https://api.looksrare.org")
        val TESTNET_LOOKSRARE_ENDPOINT: URI = URI.create("https://api-goerli.looksrare.org")
    }
}