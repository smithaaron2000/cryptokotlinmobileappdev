package org.wit.crypto.console.main

import mu.KotlinLogging
import org.wit.crypto.console.models.CryptoModel

private val logger = KotlinLogging.logger {}

val cryptos = ArrayList<CryptoModel>()

fun main(args: Array<String>) {
    logger.info { "Launching Crypto Console App" }
    println("Crypto Kotlin App Version 1.0")

    var input: Int

    do {
        input = menu()
        when(input) {
            1 -> addCrypto()
            2 -> updateCrypto()
            3 -> listCryptos()
            4 -> searchCrypto()
            -99 -> dummyData()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting Down Crypto Console App" }
}

fun menu() : Int {

    var option : Int
    var input: String?

    println("MAIN MENU")
    println(" 1. Add Cryptocurrency")
    println(" 2. Update Cryptocurrency Current Price")
    println(" 3. List All Cryptocurrencies")
    println(" 4. Search Cryptocurrencies")
    println("-1. Exit")
    println()
    print("Enter Option : ")
    input = readLine()!!
    option = if (input.toIntOrNull() != null && !input.isEmpty())
        input.toInt()
    else
        -9
    return option
}

fun addCrypto(){
    var aCrypto = CryptoModel()
    println("Add Cryptocurrency")
    println()
    print("Enter a Name : ")
    aCrypto.name = readLine()!!
    print("Enter a Symbol : ")
    aCrypto.symbol = readLine()!!
    print("Enter price per coin (USD) when you invested: ")
    aCrypto.initial_price_usd = readLine()!!.toDouble()
    print("Enter amount (USD) ou invested: ")
    aCrypto.amount_invested_usd = readLine()!!.toDouble()
    print("Enter the current price per coin (USD): ")
    aCrypto.current_price_usd = readLine()!!.toDouble()




    if (aCrypto.name.isNotEmpty() && aCrypto.symbol.isNotEmpty() && aCrypto.initial_price_usd != null && aCrypto.amount_invested_usd != null
    && aCrypto.current_price_usd != null) {
        aCrypto.id = cryptos.size.toLong()
        cryptos.add(aCrypto.copy())
        logger.info("Cryptocurrency Added : [ $aCrypto ]")
    }
    else
        logger.info("Cryptocurrency Not Added")
}

fun updateCrypto() {
    println("Update Cryptocurrency Current Price")
    println()
    listCryptos()
    var searchId = getId()
    val aCrypto = search(searchId)
    var tempCurrentPrice: Double?

    if(aCrypto != null) {
        print("Enter a new Current Price per coin for [ " + aCrypto.name + " ] : ")
        tempCurrentPrice = readLine()!!.toDouble()


        if (tempCurrentPrice != null) {
            aCrypto.current_price_usd = tempCurrentPrice
            println(
                "You updated [ " + aCrypto.current_price_usd + " ] for current price per coin (USD) ")
            logger.info("Cryptocurrency Current Price Updated : [ $aCrypto ]")
        }
        else
            logger.info("Placemark Not Updated")
    }
    else
        println("Cryptocurrency Current Price Not Updated...")
}

fun listCryptos() {
    println("List All Cryptocurrencies")
    println()
    cryptos.forEach { logger.info("${it}") }
    println()
}

fun searchCrypto() {

    var searchId = getId()
    val aCrypto = search(searchId)

    if(aCrypto != null)
        println("Cryptocurrency Details [ $aCrypto ]")
    else
        println("Cryptocurrency Not Found...")
}

fun getId() : Long {
    var strId : String? // String to hold user input
    var searchId : Long // Long to hold converted id
    print("Enter id to Search/Update : ")
    strId = readLine()!!
    searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
        strId.toLong()
    else
        -9
    return searchId
}

fun search(id: Long) : CryptoModel? {
    var foundCrypto: CryptoModel? = cryptos.find { c -> c.id == id }
    return foundCrypto
}

fun dummyData() {
    cryptos.add(CryptoModel(id = 0, name = "Bitcoin", symbol = "BTC", initial_price_usd = 53127.00, amount_invested_usd = 1000.00, current_price_usd = 54671.00, num_shares = 0.01882282, investment_value = 1029.06244))
    cryptos.add(CryptoModel(id = 1, name = "Ethereum", symbol = "ETH", initial_price_usd = 3437.00, amount_invested_usd = 500.00, current_price_usd = 3217.00, num_shares = 0.1454751, investment_value = 467.995345))
    cryptos.add(CryptoModel(id = 2, name = "Cardano", symbol = "ADA", initial_price_usd = 2.25, amount_invested_usd = 1600.00, current_price_usd = 2.67, num_shares = 711.111111, investment_value = 1898.66667))
}