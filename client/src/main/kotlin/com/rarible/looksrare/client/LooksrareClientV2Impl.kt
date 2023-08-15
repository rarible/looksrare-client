package com.rarible.looksrare.client

import com.rarible.looksrare.client.agent.UserAgentProvider
import com.rarible.looksrare.client.model.LooksrareResult
import com.rarible.looksrare.client.model.v2.EventsRequest
import com.rarible.looksrare.client.model.v2.LooksrareEvent
import com.rarible.looksrare.client.model.v2.LooksrareOrder
import com.rarible.looksrare.client.model.v2.LooksrareResponse
import com.rarible.looksrare.client.model.v2.OrdersRequest
import java.net.URI

class LooksrareClientV2Impl(
    endpoint: URI,
    apiKey: String?,
    userAgentProvider: UserAgentProvider?,
    proxy: URI?,
    logRawJson: Boolean = false
) : LooksrareClientV2, AbstractLooksrareClient(endpoint, apiKey, userAgentProvider, proxy, logRawJson) {

    override suspend fun getOrders(request: OrdersRequest): LooksrareResult<LooksrareResponse<LooksrareOrder>> {
        val uri = uriBuilderFactory.builder().run {
            path("/api/v2/orders")
            queryParam("quoteType", request.quoteType.value)
            request.pagination?.let { pagination ->
                pagination.first?.let {
                    queryParam("pagination[first]", it.toString())
                }
                pagination.cursor?.let {
                    queryParam("pagination[cursor]", it)
                }
            }
            request.sort?.let { sort ->
                queryParam(OrdersRequest::sort.name, sort.name)
            }
            request.status?.let {
                queryParam("status", request.status)
            }
            request.collection?.let {
                queryParam("collection", it.toString())
            }
            request.itemId?.let {
                queryParam("itemId", it)
            }
            build()
        }
        return getLooksrareResult(uri)
    }

    override suspend fun getEvents(request: EventsRequest): LooksrareResult<LooksrareResponse<LooksrareEvent>> {
        val uri = uriBuilderFactory.builder().run {
            path("/api/v2/events")
            request.pagination?.let { pagination ->
                pagination.first?.let {
                    queryParam("pagination[first]", it.toString())
                }
                pagination.cursor?.let {
                    queryParam("pagination[cursor]", it)
                }
            }
            request.collection?.let {
                queryParam("collection", it.toString())
            }
            request.itemId?.let {
                queryParam("itemId", it)
            }
            request.from?.let {
                queryParam("from", it.toString())
            }
            request.to?.let {
                queryParam("to", it.toString())
            }
            request.type?.let {
                queryParam("type", it.toString())
            }
            build()
        }
        return getLooksrareResult(uri)
    }
}