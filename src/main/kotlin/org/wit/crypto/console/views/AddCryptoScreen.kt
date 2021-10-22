package org.wit.crypto.console.views

import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import org.wit.crypto.console.controllers.CryptoUIController
import tornadofx.*

class AddCryptoScreen : View("Add Cryptocurrency") {
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

    override val root = form {
        setPrefSize(1000.0, 600.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            field("Name") {
                textfield(_name).required()
            }
            field("Symbol") {
                textfield(_symbol).required()
            }
            field("Initial Price per coin (USD)") {
                textfield(_initial_price_usd).required()
            }
            field("Amount Invested (USD)") {
                textfield(_amount_invested).required()
            }
            field("Current Price per coin (USD)") {
                textfield(_current_price_usd).required()
            }
            button("Add") {
                enableWhen(model.valid)
                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        cryptoUIController.add(_name.value, _symbol.value, _initial_price_usd.value, _amount_invested.value,
                                               _num_shares.value, _current_price_usd.value, _investment_value.value, _return_on_investment.value)

                    }
                }
            }
            button("Close") {
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        cryptoUIController.closeAdd()
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
