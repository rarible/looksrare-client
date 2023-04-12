package com.rarible.looksrare.client.model.v2

import io.daonomic.rpc.domain.Binary
import io.daonomic.rpc.domain.Word
import scalether.domain.Address
import java.math.BigInteger
import java.time.Instant

data class LooksrareOrder(
    val id: String,

    val hash: Word,

    val quoteType: QuoteType,

    val globalNonce: BigInteger,

    val subsetNonce: BigInteger,

    val orderNonce: BigInteger,

    val collection: Address,

    val currency: Address,

    val signer: Address,

    val strategyId: Long,

    val collectionType: Long,

    // Represented as seconds, default JSR310 parser able to handle it in right way
    val startTime: Instant,

    val endTime: Instant,

    val price: BigInteger,

    val additionalParameters: Binary,

    val signature: Binary,

    val createdAt: Instant,

    val merkleRoot: Binary?,

    val merkleProof: List<MerkleProof>?,

    val amounts: List<BigInteger>,

    val itemIds: List<BigInteger>,

    val status: Status,
)

