package com.rarible.looksrare.client

import com.rarible.looksrare.client.agent.UserAgentProvider
import com.rarible.looksrare.client.model.LooksrareResult
import com.rarible.looksrare.client.model.v2.LooksrareOrders
import com.rarible.looksrare.client.model.v2.OrdersRequest
import java.net.URI

class LooksrareClientV2Impl(
    endpoint: URI,
    apiKey: String?,
    userAgentProvider: UserAgentProvider?,
    proxy: URI?,
    logRawJson: Boolean = false
) : LooksrareClientV2, AbstractLooksrareClient(endpoint, apiKey, userAgentProvider, proxy, logRawJson) {

    override suspend fun getOrders(request: OrdersRequest): LooksrareResult<LooksrareOrders> {
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
            build()
        }
        return getOpenSeaResult(uri)
    }
}