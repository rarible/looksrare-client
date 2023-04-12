package com.rarible.looksrare.client

import com.rarible.looksrare.client.agent.UserAgentProvider
import com.rarible.looksrare.client.model.v1.LooksrareOrder
import com.rarible.looksrare.client.model.v1.OrdersRequest
import com.rarible.looksrare.client.model.v1.Pagination
import com.rarible.looksrare.client.model.v1.Sort
import com.rarible.looksrare.client.model.v1.Status
import com.rarible.looksrare.client.model.v2.QuoteType
import io.daonomic.rpc.domain.Word
import com.rarible.looksrare.client.model.v2.LooksrareOrder as LooksrareOrderV2
import com.rarible.looksrare.client.model.v2.OrdersRequest as OrdersRequestV2
import com.rarible.looksrare.client.model.v2.Pagination as PaginationV2
import com.rarible.looksrare.client.model.v2.Sort as SortV2
import com.rarible.looksrare.client.model.v2.Status as StatusV2

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertTrue
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
        logRawJson = true
    )
    private val clientV2 = LooksrareClientV2Impl(
        endpoint = URI.create("https://api.looksrare.org/"),
        apiKey = "test",
        userAgentProvider = UserAgentProvider.empty(),
        proxy = null,
        logRawJson = true
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

    @Test
    fun `should get all orders in 10 pages for v2`() = runBlocking {
        val orders = mutableListOf<LooksrareOrderV2>()
        var lastId: String? = null

        for (i in 1..2) {
            val request = OrdersRequestV2(
                quoteType = QuoteType.ASK,
                pagination = PaginationV2(lastId, 50),
                status = null,
                sort = SortV2.NEWEST,
            )
            val page = clientV2.getOrders(request).ensureSuccess().data
            orders.addAll(page)
            lastId = page.last().id
        }
        Assertions.assertEquals(100, orders.size)
    }

    @Test
    fun `should `() = runBlocking<Unit> {
        val startTime = Instant.ofEpochSecond(1676623524L)
        val request = OrdersRequest(
            isOrderAsk = true,
            startTime = startTime,
            endTime = null,
            // This param breaks startTime filtering
//            status = listOf(Status.VALID),
            status = null,
            sort = Sort.NEWEST,
            pagination = null
        )
        val page = client.getOrders(request).ensureSuccess().data
        assertTrue(page.all { it.startTime <= startTime })
    }
}
