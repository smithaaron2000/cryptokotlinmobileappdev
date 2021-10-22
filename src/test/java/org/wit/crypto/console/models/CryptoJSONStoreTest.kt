package org.wit.crypto.console.models

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

internal class CryptoJSONStoreTest {
    val cryptos = CryptoJSONStore(true)
    var aCryptoCorrect = CryptoModel(name = "Bitcoin", symbol = "BTC", initial_price_usd = 50000.0, amount_invested_usd = 1000.0,
        num_shares = 0.01818181818181818, current_price_usd = 65000.0, investment_value = 1181.8181818181818, return_on_investment = 181.8181818181818)

    var aCryptoInCorrect = CryptoModel(name = "Bitcoin", symbol = "BTC", initial_price_usd = 50000.0, amount_invested_usd = 1000.0,
        num_shares = 1.723443, current_price_usd = 65000.0, investment_value = 2000.0, return_on_investment = 900.0)

    var size = cryptos.getSize()

    @BeforeEach
    internal fun setUp() {
        cryptos.create(aCryptoCorrect)
    }

    internal fun tearDown() {
        cryptos.delete(aCryptoCorrect)
    }

    @Test
    fun create() {
        size = cryptos.getSize()
        cryptos.create(aCryptoCorrect)
        assertEquals(size + 1, cryptos.getSize())
    }


    @Test
    fun delete() {
        size = cryptos.getSize()
        cryptos.delete(aCryptoCorrect)
        assertEquals(size - 1, cryptos.getSize())
    }

    fun update() {

    }
}