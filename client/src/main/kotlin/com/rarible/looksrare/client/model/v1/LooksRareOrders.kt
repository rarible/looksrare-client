package com.rarible.looksrare.client.model.v1

data class LooksRareOrders(
    val success: Boolean,
    val message: String?,
    val data: List<LooksRareOrder>
)

