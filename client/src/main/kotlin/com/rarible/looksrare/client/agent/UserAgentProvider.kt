package com.rarible.looksrare.client.agent

interface UserAgentProvider {
    fun get(): String

    companion object {
        fun empty() = object : UserAgentProvider {
            override fun get() = ""
        }
    }
}


