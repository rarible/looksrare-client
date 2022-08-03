package com.rarible.looksrare.client

import com.rarible.looksrare.client.model.v1.LooksrareOrders
import com.rarible.looksrare.client.model.LooksrareResult
import com.rarible.looksrare.client.model.v1.OrdersRequest

interface LooksrareClient {
    suspend fun getOrders(request: OrdersRequest): LooksrareResult<LooksrareOrders>
}

