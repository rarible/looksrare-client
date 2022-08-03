package com.rarible.looksrare.client.model.v1

data class LooksrareOrders(
    val success: Boolean,
    val message: String?,
    val data: List<LooksrareOrder>
)

