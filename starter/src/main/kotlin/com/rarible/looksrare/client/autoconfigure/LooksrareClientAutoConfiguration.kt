package com.rarible.looksrare.client.autoconfigure

import com.rarible.looksrare.client.LooksrareClient
import com.rarible.looksrare.client.LooksrareClientImpl
import com.rarible.looksrare.client.LooksrareClientV2
import com.rarible.looksrare.client.LooksrareClientV2Impl
import com.rarible.looksrare.client.agent.UserAgentProvider
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import java.net.URI

@EnableConfigurationProperties(LooksrareClientProperties::class)
class LooksrareClientAutoConfiguration(
    private val properties: LooksrareClientProperties,
    private val userAgentProvider: UserAgentProvider
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @Bean
    @ConditionalOnMissingBean(LooksrareClient::class)
    fun looksrareClient(): LooksrareClient {

        val endpoint = properties.endpoint ?: if (properties.testnet) TESTNET_LOOKSRARE_ENDPOINT else LOOKSRARE_ENDPOINT

        logger.info("Creating LooksRare client with params: endpoint={}, apiKey={}, changeUserAgent={}, proxy={}",
            endpoint,
            properties.apiKey?.let { "*SPECIFIED*" },
            properties.changeUserAgent,
            properties.proxy?.let { "*SPECIFIED*" }
        )

        return LooksrareClientImpl(
            endpoint = endpoint,
            apiKey = properties.apiKey,
            userAgentProvider = if (properties.changeUserAgent) userAgentProvider else null,
            proxy = properties.proxy,
            logRawJson = properties.logRawJson
        )
    }

    @Bean
    @ConditionalOnMissingBean(LooksrareClientV2::class)
    fun looksrareClientV2(): LooksrareClientV2 {
        val endpoint = properties.endpoint ?: if (properties.testnet) TESTNET_LOOKSRARE_ENDPOINT else LOOKSRARE_ENDPOINT

        logger.info("Creating LooksRare client V2 with params: endpoint={}, apiKey={}, changeUserAgent={}, proxy={}",
            endpoint,
            properties.apiKey?.let { "*SPECIFIED*" },
            properties.changeUserAgent,
            properties.proxy?.let { "*SPECIFIED*" }
        )

        return LooksrareClientV2Impl(
            endpoint = endpoint,
            apiKey = properties.apiKey,
            userAgentProvider = if (properties.changeUserAgent) userAgentProvider else null,
            proxy = properties.proxy,
            logRawJson = properties.logRawJson
        )
    }

    private companion object {
        // https://docs.looksrare.org/ru/developers/public-api-documentation
        val LOOKSRARE_ENDPOINT: URI = URI.create("https://api.looksrare.org")
        val TESTNET_LOOKSRARE_ENDPOINT: URI = URI.create("https://api-sepolia.looksrare.org")
    }
}