package com.rarible.looksrare.client

import com.rarible.looksrare.client.model.LooksrareResult
import com.rarible.looksrare.client.model.v2.EventsRequest
import com.rarible.looksrare.client.model.v2.LooksrareEvent
import com.rarible.looksrare.client.model.v2.LooksrareOrder
import com.rarible.looksrare.client.model.v2.LooksrareResponse
import com.rarible.looksrare.client.model.v2.OrdersRequest

interface LooksrareClientV2 {
    suspend fun getOrders(request: OrdersRequest): LooksrareResult<LooksrareResponse<LooksrareOrder>>
    suspend fun getEvents(request: EventsRequest): LooksrareResult<LooksrareResponse<LooksrareEvent>>
}

