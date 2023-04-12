package com.rarible.looksrare.client

import com.rarible.looksrare.client.model.LooksrareResult
import com.rarible.looksrare.client.model.v2.LooksrareOrders
import com.rarible.looksrare.client.model.v2.OrdersRequest

interface LooksrareClientV2 {
    suspend fun getOrders(request: OrdersRequest): LooksrareResult<LooksrareOrders>
}

