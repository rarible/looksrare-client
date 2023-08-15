package com.rarible.looksrare.client.model.v2

data class LooksrareResponse<T>(
    val success: Boolean,
    val message: String?,
    val data: List<T>
)

