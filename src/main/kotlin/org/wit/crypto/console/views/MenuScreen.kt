package org.wit.crypto.console.views

import javafx.application.Platform
import javafx.geometry.Orientation
import org.wit.crypto.console.controllers.CryptoUIController
import tornadofx.*

class MenuScreen : View("Cryptocurrency Main Menu") {

    val cryptoUIController: CryptoUIController by inject()

    override val root = form {
        setPrefSize(1000.0, 600.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            text("")
            button("Add Cryptocurrency") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        cryptoUIController.loadAddScreen()
                    }
                }
            }
            text("")
            button("List Cryptocurrencies") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        cryptoUIController.loadListScreen()
                    }
                }
            }
            text("")
            button("Update Cryptocurrency Current Price") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        cryptoUIController.loadUpdateScreen()
                    }
                }
            }
            text("")
            button("Delete Cryptocurrency") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        cryptoUIController.loadDeleteScreen()
                    }
                }
            }
            text("")
            button("Exit") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        Platform.exit();
                        System.exit(0);
                    }
                }
            }
        }

    }


}