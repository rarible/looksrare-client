package com.rarible.looksrare.client.model.v2

import java.time.Instant

interface LooksrareObject {
    val id: String
    val createdAt: Instant
}