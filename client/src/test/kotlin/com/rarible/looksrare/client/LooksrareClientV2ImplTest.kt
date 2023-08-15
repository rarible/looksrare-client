package com.rarible.looksrare.client

import com.rarible.looksrare.client.agent.UserAgentProvider
import com.rarible.looksrare.client.model.v2.EventsRequest
import com.rarible.looksrare.client.model.v2.LooksrareEventType
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.net.URI

internal class LooksrareClientV2ImplTest {

    private lateinit var looksrareClientV2: LooksrareClientV2
    private lateinit var looksRareServer: MockWebServer

    @BeforeEach
    fun before() {
        looksRareServer = MockWebServer()
        looksRareServer.start()
        looksrareClientV2 = LooksrareClientV2Impl(
            endpoint = URI.create("http://127.0.0.1:${looksRareServer.port}/"),
            apiKey = "test",
            userAgentProvider = UserAgentProvider.empty(),
            proxy = null,
            logRawJson = false,
        )
    }

    @Test
    fun getEvents() = runBlocking<Unit> {
        looksRareServer.enqueue(
            MockResponse().setBody(
                LooksrareClientV2ImplTest::class.java.getResourceAsStream("/getEventsResponse.json")
                    .use { String(it.readAllBytes()) })
        )

        val result = looksrareClientV2.getEvents(EventsRequest(type = LooksrareEventType.CANCEL_LIST))

        assertThat(result.isSuccess).isTrue()
        val response = result.ensureSuccess()
        assertThat(response.success).isTrue()
        assertThat(response.data).hasSize(20)
        assertThat(response.data[0].order!!.hash.toString())
            .isEqualTo("0xa863e281d20eee5ebc555f620acd1d79fe9fa41d753541831f0826f7f17094cc")
    }
}