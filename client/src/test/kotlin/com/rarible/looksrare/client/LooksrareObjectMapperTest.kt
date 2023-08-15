package com.rarible.looksrare.client

import com.rarible.looksrare.client.model.v1.LooksrareOrder
import com.rarible.looksrare.client.model.v1.Status
import io.daonomic.rpc.domain.Binary
import io.daonomic.rpc.domain.Word
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import scalether.domain.Address
import java.math.BigInteger
import java.time.Instant

class LooksrareObjectMapperTest {

    @Test
    fun test() {
        val json = String(LooksrareObjectMapperTest::class.java.getResourceAsStream("/order.json")
            .use { it.readBytes() })
        val order = LooksrareObjectMapper.mapper.readValue(json, LooksrareOrder::class.java)

        assertThat(order.hash).isEqualTo(
            Word.apply("0x68755730c7fc96e6802a9631d627614f2f5ca4bc9b90e27b89c4112a516fa454")
        )
        assertThat(order.collectionAddress).isEqualTo(Address.apply("0x42fc55025e8bfec14f55dc8fcebc7861157c59a6"))
        assertThat(order.tokenId).isEqualTo(BigInteger.valueOf(2))
        assertThat(order.isOrderAsk).isEqualTo(true)
        assertThat(order.signer).isEqualTo(Address.apply("0xa929a26faed421e03c1af272fc93ac3a4a7fc661"))
        assertThat(order.strategy).isEqualTo(Address.apply("0xc771c0a3a7d738a1e12aa88829a658baefb32f0f"))
        assertThat(order.currencyAddress).isEqualTo(Address.apply("0xb4fbf271143f4fbf7b91a5ded31805e42b2208d6"))
        assertThat(order.amount).isEqualTo(BigInteger.ONE)
        assertThat(order.price).isEqualTo(BigInteger("999999999000000000000000000"))
        assertThat(order.nonce).isEqualTo(2)
        assertThat(order.startTime).isEqualTo(Instant.ofEpochSecond(1664150625))
        assertThat(order.endTime).isEqualTo(Instant.ofEpochSecond(1666742618))
        assertThat(order.minPercentageToAsk).isEqualTo(9800)
        assertThat(order.params).isEqualTo(Binary.apply())
        assertThat(order.status).isEqualTo(Status.VALID)
        assertThat(order.signature).isEqualTo(
            Binary.apply(
                "0x2eb25b7ff1f9a0ee20e7c6928f92997c1f7cd5ae69f88b7c162057f5832ae1dd4e0b3cf1f262c10090beff13fd8fe9c798bab4459f509389240ea99407d5e4361b"
            )
        )
        assertThat(order.v).isEqualTo(27)
        assertThat(order.r).isEqualTo(Word.apply("0x2eb25b7ff1f9a0ee20e7c6928f92997c1f7cd5ae69f88b7c162057f5832ae1dd"))
        assertThat(order.s).isEqualTo(Word.apply("0x4e0b3cf1f262c10090beff13fd8fe9c798bab4459f509389240ea99407d5e436"))
    }
}