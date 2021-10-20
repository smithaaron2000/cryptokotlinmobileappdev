package org.wit.crypto.console.views

import org.wit.crypto.console.main.cryptos
import org.wit.crypto.console.models.CryptoJSONStore
import org.wit.crypto.console.models.CryptoModel

class CryptoView {

    fun menu() : Int {

        var option : Int
        var input: String?

        println("MAIN MENU")
        println(" 1. Add Cryptocurrency")
        println(" 2. Update Cryptocurrency")
        println(" 3. List All Cryptocurrencies")
        println(" 4. Search all Cryptocurrencies")
        println(" 5. Delete Cryptocurrency")
        println(" 6. View Crypto Wallet")
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

    fun listCryptos(cryptos: CryptoJSONStore) {
        cryptos.logAll()
        println()
    }

    fun showCrypto(crypto : CryptoModel) {
        if(crypto != null)
            println("Crypto Details [ $crypto ]")
        else
            println("Crypto Not Found...")
    }

    fun addCryptoData(crypto: CryptoModel) : Boolean {

        println()
        print("Enter a Name : ")
        crypto.name = readLine()!!
        print("Enter a Symbol : ")
        crypto.symbol = readLine()!!
        var initial_flag = true
        while(initial_flag == true) {
            print("Enter price  per coin (USD) when you invested: ")
            try {
                crypto.initial_price_usd = readLine()!!.toDouble()
                initial_flag = false
            } catch (e: Exception) {
                initial_flag = true
            }
        }

        var invest_flag = true
        while(invest_flag == true) {
            print("Enter amount (USD) you invested: ")
            try {
                crypto.amount_invested_usd = readLine()!!.toDouble()
                invest_flag = false
            } catch (e: Exception) {
                invest_flag = true
            }
        }

        var current_flag = true
        while(current_flag == true) {
            print("Enter the current price per share (USD): ")
            try {
                crypto.current_price_usd = readLine()!!.toDouble()
                current_flag = false
            } catch (e: Exception) {
                current_flag = true
            }
        }

        return crypto.name.isNotEmpty() && crypto.symbol.isNotEmpty() && crypto.initial_price_usd != null && crypto.amount_invested_usd != null
                && crypto.current_price_usd != null
    }

    fun updateCryptoData(crypto: CryptoModel) : Boolean {

        var tempCurrentPrice: Double?

        if (crypto != null) {
            print("Enter a new Current Price per share (USD) for [ " + crypto.current_price_usd + " ] : ")
            tempCurrentPrice = readLine()!!.toDouble()

            if (tempCurrentPrice != null) {
                crypto.current_price_usd = tempCurrentPrice
                return true
            }
        }
        return false
    }

    fun deleteCryptoData(crypto: CryptoModel) : Boolean {

        print("Enter ID of Crypto to Delete : ")
        listCryptos(cryptos)
        var id = readLine()!!.toLong()
        var foundCrypto = cryptos.findOne(id)
        if (foundCrypto != null) {
            cryptos.delete(foundCrypto)
        }
        print("Cryptocurrency Deleted")

        return crypto.name.isEmpty() && crypto.symbol.isEmpty() && crypto.initial_price_usd == null
                && crypto.amount_invested_usd == null && crypto.current_price_usd == null
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
}


