package org.wit.crypto.console.controllers

import javafx.beans.property.SimpleDoubleProperty
import mu.KotlinLogging
import org.wit.crypto.console.models.CryptoJSONStore
import org.wit.crypto.console.models.CryptoModel
import org.wit.crypto.console.views.*
import tornadofx.*

class CryptoUIController : Controller() {

    val cryptos = CryptoJSONStore(false)
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Cryptocurrency TornadoFX UI App" }
    }
    fun add(
        _name: String, _symbol: String, _initial_price_usd: Double, _amount_invested_usd: Double, _num_shares: Double, _current_price_usd: Double,
        _investment_value: Double, _return_on_investment: Double
    )
    {

        var aCrypto = CryptoModel(name = _name, symbol = _symbol, initial_price_usd = _initial_price_usd, amount_invested_usd = _amount_invested_usd,
                                 num_shares = _num_shares, current_price_usd = _current_price_usd, investment_value = _investment_value, return_on_investment = _return_on_investment)
        cryptos.create(aCrypto)
        logger.info("Cryptocurrency Added")
    }

    fun update(aCrypto: CryptoModel, price: Double)
    {
        cryptos.update(aCrypto, price)
        logger.info("Cryptocurrency Updated")
    }

    fun delete(aCrypto: CryptoModel)
    {
        cryptos.delete(aCrypto)
        logger.info("Cryptocurrency Deleted")
    }

    fun loadListScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(ListCryptoScreen::class, sizeToScene = true, centerOnScreen = true)
        }
        cryptos.logAll()
    }

    fun loadAddScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(AddCryptoScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    fun loadUpdateScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(UpdateCryptoScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    fun loadDeleteScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(DeleteCryptoScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    fun closeAdd() {
        runLater {
            find(AddCryptoScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    fun closeUpdate() {
        runLater {
            find(UpdateCryptoScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    fun closeDelete() {
        runLater {
            find(DeleteCryptoScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    fun closeList() {
        runLater {
            find(ListCryptoScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }
}