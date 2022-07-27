package com.rarible.looksrare.client.autoconfigure

import com.rarible.looksrare.client.agent.SimpleUserAgentProviderImpl
import com.rarible.looksrare.client.agent.UserAgentProvider
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean

class LooksrareUserAgentProviderAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean(UserAgentProvider::class)
    fun looksrareUserAgentProvider(): SimpleUserAgentProviderImpl {
        return SimpleUserAgentProviderImpl()
    }
}