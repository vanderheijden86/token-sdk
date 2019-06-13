package com.r3.corda.lib.tokens.money

import java.util.*

/** A representation of digital money. This implementation somewhat mirrors that of [Currency]. */
data class DigitalCurrency(
        override val tokenIdentifier: String,
        override val description: String,
        override val fractionDigits: Int = 0
) : Money {
    override fun toString(): String = tokenIdentifier

    constructor(currencyCode: String) : this(
            getInstance(currencyCode).tokenIdentifier,
            getInstance(currencyCode).description,
            getInstance(currencyCode).fractionDigits
    )

    companion object {
        private val registry = mapOf(
                Pair("XRP", DigitalCurrency("XRP", "Ripple", 6)),
                Pair("BTC", DigitalCurrency("BTC", "Bitcoin", 8)),
                Pair("ETH", DigitalCurrency("ETH", "Ethereum", 18)),
                Pair("DOGE", DigitalCurrency("DOGE", "Dogecoin", 8))
        )

        fun getInstance(currencyCode: String): DigitalCurrency {
            return registry[currencyCode] ?: throw IllegalArgumentException("$currencyCode doesn't exist.")
        }
    }
}