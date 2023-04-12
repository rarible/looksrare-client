package com.rarible.looksrare.client.model.v2

data class LooksrareOrders(
    val success: Boolean,
    val message: String?,
    val data: List<LooksrareOrder>
)

