package com.nix.summer.finall

import java.lang.System.exit

class Controller(private val model: Model) {

    private lateinit var view: MainActivity

    fun attachView(_view: MainActivity) {
        view = _view
    }

    fun takeCommand(command: String) {
        when (command) {
            "ESPRESSO" -> {
                view.setStatus(model.buy(Coffee.ESPRESSO))
            }
            "LATTE" -> {
                view.setStatus(model.buy(Coffee.LATTE))
            }
            "CAPPUCCINO" -> {
                view.setStatus(model.buy(Coffee.CAPPUCCINO))
            }
            "take" -> {
                view.takeMoney(model.take())
            }
            "remaining" -> {
                view.showResources(model.remaining())
            }
            else -> println(" ")
        }
        view.showResources(model.remaining())
    }

    fun fillResources(resources: Resources) {
        model.fill(resources)
        view.showResources(model.remaining())
    }

    fun start() {
        view.showResources(model.remaining())
    }
}
