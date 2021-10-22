package org.wit.crypto.console.views

import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import org.wit.crypto.console.controllers.CryptoUIController
import org.wit.crypto.console.models.CryptoModel
import tornadofx.*

class UpdateCryptoScreen : View("Update Cryptocurrency") {
    val model = ViewModel()
    val _name = model.bind { SimpleStringProperty() }
    val _symbol = model.bind { SimpleStringProperty() }
    val _initial_price_usd = model.bind { SimpleDoubleProperty() }
    val _amount_invested = model.bind { SimpleDoubleProperty() }
    val _num_shares = model.bind { SimpleDoubleProperty() }
    val _current_price_usd = model.bind { SimpleDoubleProperty() }
    val _investment_value = model.bind { SimpleDoubleProperty() }
    val _return_on_investment = model.bind { SimpleDoubleProperty() }

    val cryptoUIController: CryptoUIController by inject()
    val tableContent = cryptoUIController.cryptos.findAll()
    val data = tableContent.observable()
    var selected = CryptoModel()
    var tab = tableview(data)

    override val root = vbox {
        setPrefSize(1000.0, 600.0)
        tableview(data) {
            readonlyColumn("NAME", CryptoModel::name)
            readonlyColumn("SYMBOL", CryptoModel::symbol)
            readonlyColumn("INITIAL PRICE PER COIN (USD)", CryptoModel::initial_price_usd)
            readonlyColumn("AMOUNT INVESTED (USD)", CryptoModel::amount_invested_usd)
            readonlyColumn("NUMBER OF SHARES", CryptoModel::num_shares)
            readonlyColumn("CURRENT PRICE PER COIN (USD)", CryptoModel::current_price_usd)
            readonlyColumn("INVESTMENT VALUE", CryptoModel::investment_value)
            readonlyColumn("RETURN ON INVESTMENT", CryptoModel::return_on_investment)
            onUserSelect { crypto -> selected = (crypto) }
            this.refresh()
            tab=this
        }
    }

    val rootForm = form {
        setPrefSize(1000.0, 600.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            field("Current Price per coin (USD)") {
                textfield(_current_price_usd).required()
            }
            button("Update") {
                enableWhen(model.valid)
                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        cryptoUIController.update(selected, _current_price_usd.value)
                        tab.refresh()

                    }
                }
            }
            button("Close") {
                useMaxWidth = true
                action {
                    runAsync {
                        cryptoUIController.closeUpdate()
                        tab.refresh()
                    }
                }
            }
        }
    }

    override fun onDock() {
        _name.value = ""
        _symbol.value = ""
        _initial_price_usd.value = 0.0
        _amount_invested.value = 0.0
        _num_shares.value = 0.0
        _current_price_usd.value = 0.0
        _investment_value.value = 0.0
        _return_on_investment.value = 0.0

        model.clearDecorators()
    }
}
