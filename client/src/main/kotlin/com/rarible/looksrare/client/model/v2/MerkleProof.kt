package com.rarible.looksrare.client.model.v2

import io.daonomic.rpc.domain.Binary

data class MerkleProof(
    val position: Long,
    val value: Binary
)