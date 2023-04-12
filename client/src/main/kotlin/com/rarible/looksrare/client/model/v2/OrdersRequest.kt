package com.rarible.looksrare.client.model.v2

data class OrdersRequest(
    val quoteType: QuoteType,

    val sort: Sort?,

    val pagination: Pagination?,

    val status: Status?,
)