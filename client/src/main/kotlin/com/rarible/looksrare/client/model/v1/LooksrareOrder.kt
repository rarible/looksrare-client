package com.rarible.looksrare.client.model.v1

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.rarible.looksrare.client.deserializer.WordDeserializer
import io.daonomic.rpc.domain.Binary
import io.daonomic.rpc.domain.Word
import scalether.domain.Address
import java.math.BigInteger
import java.time.Instant

data class LooksrareOrder(

    val hash: Word,

    val collectionAddress: Address,

    val tokenId: BigInteger?,

    val isOrderAsk: Boolean,

    val signer: Address,

    val strategy: Address,

    val currencyAddress: Address,

    val amount: BigInteger,

    val price: BigInteger,

    val nonce: BigInteger,

    // Represented as seconds, default JSR310 parser able to handle it in right way
    val startTime: Instant,
    val endTime: Instant,

    val minPercentageToAsk: Int,

    val params: Binary?,

    val status: Status,

    val signature: Binary?,

    val v: Byte?,

    @JsonDeserialize(using = WordDeserializer::class)
    val r: Word?,

    @JsonDeserialize(using = WordDeserializer::class)
    val s: Word?
)
