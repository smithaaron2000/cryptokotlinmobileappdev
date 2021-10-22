package org.wit.crypto.console.controllers

import mu.KotlinLogging
import org.wit.crypto.console.models.CryptoJSONStore
import org.wit.crypto.console.models.CryptoModel
import org.wit.crypto.console.views.CryptoView

class CryptoController {

    //val cryptos = CryptoMemStore()
    val cryptos = CryptoJSONStore(false)
    val cryptoView = CryptoView()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Crypto Console App" }
        println("Crypto Kotlin App Version 1.0")
    }

    fun start() {
        var input: Int

        do {
            input = menu()
            when (input) {
                1 -> add()
                2 -> update()
                3 -> list()
                4 -> search()
                5 -> delete()
                -99 -> dummyData()
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        logger.info { "Shutting Down Crypto Console App" }
    }

    fun menu() :Int { return cryptoView.menu() }

    fun add(){
        var aCrypto = CryptoModel()

        if (cryptoView.addCryptoData(aCrypto))
            cryptos.create(aCrypto)
        else
            logger.info("Crypto Not Added")
    }

    fun list() {
        cryptoView.listCryptos(cryptos)
    }

    fun update() {

        cryptoView.listCryptos(cryptos)
        var searchId = cryptoView.getId()
        val aCrypto = search(searchId)

        if(aCrypto != null) {
            if(cryptoView.updateCryptoData(aCrypto)) {
                cryptos.update(aCrypto)
                cryptoView.showCrypto(aCrypto)
                logger.info("Crypto Updated : [ $aCrypto ]")
            }
            else
                logger.info("Crypto Not Updated")
        }
        else
            println("Crypto Not Updated...")
    }

    fun search() {
        val aCrypto = search(cryptoView.getId())!!
        cryptoView.showCrypto(aCrypto)
    }


    fun search(id: Long) : CryptoModel? {
        var foundCrypto = cryptos.findOne(id)
        return foundCrypto
    }

    fun delete() {
        cryptoView.listCryptos(cryptos)
        var searchId = cryptoView.getId()
        val aCrypto = search(searchId)

        if(aCrypto != null) {
            cryptos.delete(aCrypto)
            println("Cryptocurrency Deleted...")
            cryptoView.listCryptos(cryptos)
        }
        else
            println("Cryptocurrency Not Deleted...")
    }

    fun dummyData() {
        cryptos.create(CryptoModel(id = 0, name = "Bitcoin", symbol = "BTC", initial_price_usd = 53127.00, amount_invested_usd = 1000.00, current_price_usd = 54671.00, num_shares = 0.01882282, investment_value = 1029.06244))
        cryptos.create(CryptoModel(id = 1, name = "Ethereum", symbol = "ETH", initial_price_usd = 3437.00, amount_invested_usd = 500.00, current_price_usd = 3217.00, num_shares = 0.1454751, investment_value = 467.995345))
        cryptos.create(CryptoModel(id = 2, name = "Cardano", symbol = "ADA", initial_price_usd = 2.25, amount_invested_usd = 1600.00, current_price_usd = 2.67, num_shares = 711.111111, investment_value = 1898.66667))
    }
}