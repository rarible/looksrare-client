package com.rarible.looksrare.client.model.v1

import java.net.URLEncoder

/**
 * Pagination filter. When specified, it will return orders starting from the order with hash of `cursor`, with `first` amount.
 * @param cursor represents the order hash. Default to 20, max to 150.
 * @param first a number of record to read
 */
data class Pagination(
    /**
     * Hash od
     */
    val cursor: String?,

    val first: Int?
) {

    override fun toString(): String {
        val sb = StringBuilder("{")
        var appendComa = false
        cursor?.let {
            sb.append("\"cursor\": \"$cursor\"")
            appendComa = true
        }

        first?.let {
            if(appendComa) sb.append(",")
            sb.append("\"first\": $first")
        }

        sb.append("}")
        return URLEncoder.encode(sb.toString(), "UTF8")
    }
}