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
    print("Enter a Description : ")
    aCrypto.description = readLine()!!

    if (aCrypto.title.isNotEmpty() && aCrypto.description.isNotEmpty()) {
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
    var tempTitle : String?
    var tempDescription : String?

    if(aCrypto != null) {
        print("Enter a new Title for [ " + aCrypto.title + " ] : ")
        tempTitle = readLine()!!
        print("Enter a new Description for [ " + aPlacemark.description + " ] : ")
        tempDescription = readLine()!!

        if (!tempTitle.isNullOrEmpty() && !tempDescription.isNullOrEmpty()) {
            aPlacemark.title = tempTitle
            aPlacemark.description = tempDescription
            println(
                "You updated [ " + aPlacemark.title + " ] for title " +
                        "and [ " + aPlacemark.description + " ] for description")
            logger.info("Placemark Updated : [ $aPlacemark ]")
        }
        else
            logger.info("Placemark Not Updated")
    }
    else
        println("Placemark Not Updated...")
}

fun listPlacemarks() {
    println("List All Placemarks")
    println()
    s.forEach { logger.info("${it}") }
    println()
}

fun searchPlacemark() {

    var searchId = getId()
    val aPlacemark = search(searchId)

    if(aPlacemark != null)
        println("Placemark Details [ $aPlacemark ]")
    else
        println("Placemark Not Found...")
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

fun search(id: Long) : PlacemarkModel? {
    var foundPlacemark: PlacemarkModel? = placemarks.find { p -> p.id == id }
    return foundPlacemark
}

fun dummyData() {
    placemarks.add(PlacemarkModel(1, "New York New York", "So Good They Named It Twice"))
    placemarks.add(PlacemarkModel(2, "Ring of Kerry", "Some place in the Kingdom"))
    placemarks.add(PlacemarkModel(3, "Waterford City", "You get great Blaas Here!!"))
}