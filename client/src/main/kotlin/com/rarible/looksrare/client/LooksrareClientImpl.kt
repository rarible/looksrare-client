package com.rarible.looksrare.client

import com.rarible.looksrare.client.agent.UserAgentProvider
import com.rarible.looksrare.client.model.LooksrareResult
import com.rarible.looksrare.client.model.v1.LooksrareOrders
import com.rarible.looksrare.client.model.v1.OrdersRequest
import java.net.URI

class LooksrareClientImpl(
    endpoint: URI,
    apiKey: String?,
    userAgentProvider: UserAgentProvider?,
    proxy: URI?,
    logRawJson: Boolean = false
) : LooksrareClient, AbstractLooksrareClient(endpoint, apiKey, userAgentProvider, proxy, logRawJson) {

    override suspend fun getOrders(request: OrdersRequest): LooksrareResult<LooksrareOrders> {
        val uri = uriBuilderFactory.builder().run {
            path("/api/v1/orders")
            queryParam("isOrderAsk", request.isOrderAsk)
            request.startTime?.let { from ->
                queryParam(OrdersRequest::startTime.name, from.epochSecond.toString())
            }
            request.endTime?.let { to ->
                queryParam(OrdersRequest::endTime.name, to.epochSecond.toString())
            }
            request.pagination?.let {
                queryParam("pagination", it.toString())
            }
            request.sort?.let { sort ->
                queryParam(OrdersRequest::sort.name, sort.name)
            }
            request.status?.let {
                queryParam("status[]", (request.status))
            }
            request.collection?.let {
                queryParam("collection", it.toString())
            }
            request.tokenId?.let {
                queryParam("tokenId", it)
            }
            build()
        }
        return getLooksrareResult(uri)
    }
}
