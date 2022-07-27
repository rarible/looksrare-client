package com.rarible.looksrare.client.model.v1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PaginationTest {

    @Test
    fun `should render empty pagination`() {
        assertEquals(
            "{}",
            Pagination(null, null).toString()
        )
    }

    @Test
    fun `should render cursor pagination`() {
        assertEquals(
            "{\"cursor\": \"abcd\"}",
            Pagination("abcd", null).toString()
        )
    }

    @Test
    fun `should render first pagination`() {
        assertEquals(
            "{\"first\": 10}",
            Pagination(null, 10).toString()
        )
    }

    @Test
    fun `should render full pagination`() {
        assertEquals(
            "{\"cursor\": \"abcd\",\"first\": 10}",
            Pagination("abcd", 10).toString()
        )
    }

}