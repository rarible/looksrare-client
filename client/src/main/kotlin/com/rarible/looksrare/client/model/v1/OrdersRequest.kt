package com.rarible.looksrare.client.model.v1

import java.time.Instant

data class OrdersRequest(
    /**
     * Specifies whether the order is ask or bid. For ask (aka. listing), send true. For bid (aka. offer), send false
     */
    val isOrderAsk: Boolean,

    /**
     * Start timestamp. This accepts the string representation of the timestamp in seconds.
     */
    val startTime: Instant?,

    /**
     * End timestamp. This accepts the string representation of the timestamp in seconds.
     */
    val endTime: Instant?,

    val status: List<Status>?,

    val sort: Sort?,

    val pagination: Pagination?
)
