package org.wit.crypto.console.models

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}
var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class CryptoMemStore : CryptoStore {

    val cryptos = ArrayList<CryptoModel>()

    override fun findAll(): List<CryptoModel> {
        return cryptos
    }

    override fun findOne(id: Long) : CryptoModel? {
        var foundCrypto: CryptoModel? = cryptos.find { c -> c.id == id }
        return foundCrypto
    }

    override fun create(crypto: CryptoModel) {
        crypto.id = getId()
        crypto.num_shares = crypto.amount_invested_usd / crypto.initial_price_usd
        crypto.investment_value = crypto.num_shares * crypto.current_price_usd
        crypto.return_on_investment = crypto.investment_value - crypto.amount_invested_usd
        cryptos.add(crypto)
        logAll()
    }

    override fun update(crypto: CryptoModel) {
        var foundCrypto = findOne(crypto.id!!)
        if (foundCrypto != null) {
            foundCrypto.name = crypto.name
            foundCrypto.symbol = crypto.symbol
            foundCrypto.initial_price_usd = crypto.initial_price_usd
            foundCrypto.amount_invested_usd = crypto.amount_invested_usd
            foundCrypto.num_shares = crypto.num_shares
            foundCrypto.current_price_usd = crypto.current_price_usd
            foundCrypto.investment_value = crypto.num_shares * crypto.current_price_usd
            foundCrypto.return_on_investment = crypto.investment_value - crypto.amount_invested_usd

        }
    }

    internal fun logAll() {
        cryptos.forEach { logger.info("${it}") }
    }
}