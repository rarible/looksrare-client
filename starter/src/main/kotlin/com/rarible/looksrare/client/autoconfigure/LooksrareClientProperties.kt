package com.rarible.looksrare.client.autoconfigure

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import java.net.URI

internal const val RARIBLE_LOOKSRARE = "rarible.looksrare"

@ConstructorBinding
@ConfigurationProperties(RARIBLE_LOOKSRARE)
data class LooksrareClientProperties(
    val testnet: Boolean = false,
    val endpoint: URI? = null,
    val proxy: URI? = null,
    val apiKey: String? = null,
    val changeUserAgent: Boolean = true,
    val logRawJson: Boolean = false
)