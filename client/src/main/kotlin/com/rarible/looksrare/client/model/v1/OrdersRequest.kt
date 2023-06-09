package com.rarible.looksrare.client.model.v1

import scalether.domain.Address
import java.time.Instant

data class OrdersRequest(
    /**
     * Specifies whether the order is ask or bid. For ask (aka. listing), send true. For bid (aka. offer), send false
     */
    val isOrderAsk: Boolean = true,

    /**
     * Start timestamp. This accepts the string representation of the timestamp in seconds.
     */
    val startTime: Instant? = null,

    /**
     * End timestamp. This accepts the string representation of the timestamp in seconds.
     */
    val endTime: Instant? = null,

    val collection: Address? = null,

    val tokenId: String? = null,

    val status: List<Status>? = null,

    val sort: Sort? = Sort.NEWEST,

    val pagination: Pagination? = null,
)
