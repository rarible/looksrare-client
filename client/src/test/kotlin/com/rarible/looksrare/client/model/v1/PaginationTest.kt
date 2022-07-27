package com.rarible.looksrare.client.model.v1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PaginationTest {

    @Test
    fun `should render empty pagination`() {
        assertEquals(
            "%7B%7D",
            Pagination(null, null).toString()
        )
    }

    @Test
    fun `should render cursor pagination`() {
        assertEquals(
            "%7B%22cursor%22%3A+%22abcd%22%7D",
            Pagination("abcd", null).toString()
        )
    }

    @Test
    fun `should render first pagination`() {
        assertEquals(
            "%7B%22first%22%3A+10%7D",
            Pagination(null, 10).toString()
        )
    }

    @Test
    fun `should render full pagination`() {
        assertEquals(
            "%7B%22cursor%22%3A+%22abcd%22%2C%22first%22%3A+10%7D",
            Pagination("abcd", 10).toString()
        )
    }

}