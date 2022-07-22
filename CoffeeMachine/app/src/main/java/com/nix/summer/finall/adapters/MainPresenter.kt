package com.nix.summer.finall.adapters
import com.nix.summer.finall.core.entities.Resources
import com.nix.summer.finall.core.entities.Coffee
import com.nix.summer.finall.core.entities.Status
import com.nix.summer.finall.core.model.Model

class MainPresenter(private val model: Model) : Contract.Presenter {

    private var view: Contract.View? = null

    override fun attach(view: Contract.View) {
        this.view = view
    }

    override fun detach() {
        this.view = null
    }

    fun start() {
        view?.showResources(model.remaining())
    }

    fun takeCommand(command: String){
        when (command) {
            "ESPRESSO" -> {
                view?.setStatus(model.buy(Coffee.ESPRESSO))
            }
            "LATTE" -> {
                view?.setStatus(model.buy(Coffee.LATTE))
            }
            "CAPPUCCINO" -> {
                view?.setStatus(model.buy(Coffee.CAPPUCCINO))
            }
            "take" -> {
                view?.takeMoney(model.take())
            }
            "remaining" -> {
                view?.showResources(model.remaining())
            }
            else -> println(" ")
        }
        view?.showResources(model.remaining())
    }

    fun fillResources(resources: Resources) {
        model.fill(resources)
        view?.showResources(model.remaining())
    }

}
