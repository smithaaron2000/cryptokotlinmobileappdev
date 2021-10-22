package org.wit.crypto.console.views

import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.FXCollections
import javafx.geometry.Orientation
import javafx.scene.control.Slider
import javafx.scene.control.TableView
import javafx.scene.layout.GridPane
import org.wit.crypto.console.controllers.CryptoUIController
import org.wit.crypto.console.main.cryptos
import org.wit.crypto.console.models.CryptoJSONStore
import org.wit.crypto.console.models.CryptoModel
import tornadofx.*

class ListCryptoScreen : View("List Cryptocurrencies") {

    val cryptoUIController: CryptoUIController by inject()
    val tableContent = cryptoUIController.cryptos.findAll()
    val data = tableContent.observable()
    var tab = tableview(data)


    override val root = vbox {
        setPrefSize(1000.0, 600.0)

//        form {
//            fieldset(labelPosition = Orientation.HORIZONTAL) {
//                label("Filter by Number of Shares")
//                slider(min= 0, max = 100, orientation = Orientation.HORIZONTAL) {
//                    useMaxWidth = true
//                    if(this.isValueChanging) {
//                        runAsyncWithProgress {
//                            cryptos.filter(this.value)
//                            tableview(data).refresh()
//                            println(value)
//                        }
//                    }
//                }
//            }
//        }

        tableview(data) {
            readonlyColumn("NAME", CryptoModel::name)
            readonlyColumn("SYMBOL", CryptoModel::symbol)
            readonlyColumn("INITIAL PRICE PER COIN (USD)", CryptoModel::initial_price_usd)
            readonlyColumn("AMOUNT INVESTED (USD)", CryptoModel::amount_invested_usd)
            readonlyColumn("NUMBER OF SHARES", CryptoModel::num_shares)
            readonlyColumn("CURRENT PRICE PER COIN (USD)", CryptoModel::current_price_usd)
            readonlyColumn("INVESTMENT VALUE", CryptoModel::investment_value)
            readonlyColumn("RETURN ON INVESTMENT", CryptoModel::return_on_investment)
            this.refresh()
            tab = this
        }
        button("Close") {
            useMaxWidth = true
            action {
                runAsyncWithProgress {
                    cryptoUIController.closeList()
                }
            }
        }
    }

}