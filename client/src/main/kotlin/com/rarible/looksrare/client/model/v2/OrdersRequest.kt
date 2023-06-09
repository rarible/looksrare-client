package com.rarible.looksrare.client.model.v2

import scalether.domain.Address

data class OrdersRequest(
    val quoteType: QuoteType = QuoteType.ASK,

    val collection: Address? = null,

    val itemId: String? = null,

    val sort: Sort? = Sort.NEWEST,

    val pagination: Pagination? = null,

    val status: Status? = Status.VALID,
)