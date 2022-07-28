package com.rarible.looksrare.client.model.v1

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.rarible.looksrare.client.deserializer.WordDeserializer
import io.daonomic.rpc.domain.Binary
import io.daonomic.rpc.domain.Word
import scalether.domain.Address
import java.math.BigDecimal
import java.math.BigInteger
import java.time.Instant

data class LooksRareOrder(

    val hash: Word,

    val collectionAddress: Address,

    val tokenId: BigInteger?,

    val isOrderAsk: Boolean,

    val signer: Address,

    val strategy: Address,

    val currencyAddress: Address,

    val amount: Int,

    val price: BigInteger,

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="s")
    val startTime: Instant,

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="s")
    val endTime: Instant,

    val minPercentageToAsk: Int,

    val params: String?,

    val status: Status,

    val signature: Binary?,

    val v: Byte?,

    @JsonDeserialize(using = WordDeserializer::class)
    val r: Word?,

    @JsonDeserialize(using = WordDeserializer::class)
    val s: Word?
)
