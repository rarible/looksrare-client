package com.rarible.looksrare.client

import com.rarible.looksrare.client.agent.UserAgentProvider
import com.rarible.looksrare.client.model.v1.LooksrareOrder
import com.rarible.looksrare.client.model.v1.OrdersRequest
import com.rarible.looksrare.client.model.v1.Pagination
import com.rarible.looksrare.client.model.v1.Sort
import com.rarible.looksrare.client.model.v1.Status
import io.github.resilience4j.ratelimiter.RateLimiter
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.net.URI
import java.time.Instant

@Disabled
internal class LooksrareClientTest {
    private val client = LooksrareClientImpl(
        endpoint = URI.create("https://api.looksrare.org/"),
        apiKey = "test",
        userAgentProvider = UserAgentProvider.empty(),
        proxy = null,
        logRawJson = true,
        rateLimiter = RateLimiter.ofDefaults("test")
    )

    @Test
    fun `should get all orders in 10 pages`() = runBlocking {
        val orders = mutableListOf<LooksrareOrder>()
        val listedBefore = Instant.now()
        var lastHash: String? = null

        for (i in 1..2) {
            val request = OrdersRequest(
                pagination = Pagination(lastHash, 50),
                isOrderAsk = true,
                status = listOf(Status.VALID, Status.EXECUTED),
                sort = Sort.NEWEST,
                endTime = listedBefore,
                startTime = null
            )
            val page = client.getOrders(request).ensureSuccess().data
            orders.addAll(page)
            lastHash = page.last().hash.toString()
        }
        Assertions.assertEquals(100, orders.size)
    }
}
