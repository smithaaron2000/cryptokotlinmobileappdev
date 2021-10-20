package org.wit.crypto.console.main

import mu.KotlinLogging
import org.wit.crypto.console.models.CryptoModel
import org.wit.crypto.console.views.CryptoView
import org.wit.crypto.console.controllers.CryptoController
import org.wit.crypto.console.models.CryptoJSONStore

private val logger = KotlinLogging.logger {}

val cryptos = CryptoJSONStore()
//val cryptos = CryptoMemStore()
val cryptoView = CryptoView()

fun main(args: Array<String>) {
    logger.info { "Launching Crypto Console App" }
    println("Crypto Kotlin App Version 1.0")

    var input: Int
    val controller = CryptoController()

    do {
        input = cryptoView.menu()
        when(input) {
            1 -> addCrypto()
            2 -> updateCrypto()
            3 -> cryptoView.listCryptos(cryptos)
            4 -> searchCrypto()
            5 -> deleteCrypto()
            6 -> viewWallet()
            -99 -> dummyData()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting Down Crypto Console App" }
}


fun addCrypto(){
    var aCrypto = CryptoModel()

    if (cryptoView.addCryptoData(aCrypto))
        cryptos.create(aCrypto)
    else
        logger.info("Crypto Not Added")
}

fun updateCrypto() {

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

fun searchCrypto() {
    val aCrypto = search(cryptoView.getId())!!
    cryptoView.showCrypto(aCrypto)
}

fun deleteCrypto(){
    var aCrypto = CryptoModel()

    if (cryptoView.deleteCryptoData(aCrypto))
        cryptos.delete(aCrypto)
}


fun search(id: Long) : CryptoModel? {
    var foundCrypto = cryptos.findOne(id)
    return foundCrypto
}

fun viewWallet() {
    //take current worth of each crypto (amount invested * current price)
    //take crypto worth and add them all together
    //show current worth of wallet
    //show return on investment as a %
}

fun dummyData() {
    cryptos.create(CryptoModel(id = 0, name = "Bitcoin", symbol = "BTC", initial_price_usd = 53127.00, amount_invested_usd = 1000.00, current_price_usd = 54671.00, num_shares = 0.01882282, investment_value = 1029.06244))
    cryptos.create(CryptoModel(id = 1, name = "Ethereum", symbol = "ETH", initial_price_usd = 3437.00, amount_invested_usd = 500.00, current_price_usd = 3217.00, num_shares = 0.1454751, investment_value = 467.995345))
    cryptos.create(CryptoModel(id = 2, name = "Cardano", symbol = "ADA", initial_price_usd = 2.25, amount_invested_usd = 1600.00, current_price_usd = 2.67, num_shares = 711.111111, investment_value = 1898.66667))
}