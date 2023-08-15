package com.rarible.looksrare.client.model.v2

import scalether.domain.Address

class EventsRequest(
    val collection: Address? = null,
    val from: Address? = null,
    val to: Address? = null,
    val itemId: String? = null,
    val type: LooksrareEventType? = null,
    val pagination: Pagination? = null,
)