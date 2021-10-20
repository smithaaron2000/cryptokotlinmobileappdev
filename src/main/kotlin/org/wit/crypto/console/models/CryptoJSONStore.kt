package org.wit.crypto.console.models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging

import org.wit.crypto.console.helpers.*
import java.util.*

private val logger = KotlinLogging.logger {}

val JSON_FILE = "cryptos.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<CryptoModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class CryptoJSONStore : CryptoStore {

    var cryptos = mutableListOf<CryptoModel>()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<CryptoModel> {
        return cryptos
    }

    override fun findOne(id: Long) : CryptoModel? {
        var foundCrypto: CryptoModel? = cryptos.find { c -> c.id == id }
        return foundCrypto
    }

    override fun create(crypto: CryptoModel) {
        crypto.id = generateRandomId()
        crypto.num_shares = crypto.amount_invested_usd / crypto.initial_price_usd
        crypto.investment_value = crypto.num_shares * crypto.current_price_usd
        crypto.return_on_investment = crypto.investment_value - crypto.amount_invested_usd
        cryptos.add(crypto)
        serialize()
    }

    override fun delete(crypto: CryptoModel) {
        cryptos.remove(crypto)
        serialize()
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
        serialize()
    }

    internal fun logAll() {
        cryptos.forEach { logger.info("${it}") }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(cryptos, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        cryptos = Gson().fromJson(jsonString, listType)
    }
}