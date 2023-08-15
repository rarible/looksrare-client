package com.rarible.looksrare.client.model.v2

import scalether.domain.Address
import java.time.Instant

data class LooksrareEvent(
    override val id: String,
    val from: Address,
    val to: Address? = null,
    val type: LooksrareEventType,
    val hash: String? = null,
    override val createdAt: Instant,
    val collection: LooksrareCollection? = null,
    val token: LooksrareToken? = null,
    val order: LooksrareOrder? = null,
): LooksrareObject