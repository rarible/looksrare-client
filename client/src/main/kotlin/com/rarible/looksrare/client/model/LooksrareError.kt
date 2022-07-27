package com.rarible.looksrare.client.model

data class LooksrareError(
    val httpCode: Int,
    val code: LooksrareErrorCode,
    val message: String
)
