package com.nix.summer.finall.adapters
import com.nix.summer.finall.core.entities.Resources
import com.nix.summer.finall.core.entities.Coffee
import com.nix.summer.finall.core.entities.Status
import com.nix.summer.finall.core.interactors.BuyCoffeeInteractor
import com.nix.summer.finall.core.interactors.FillResourcesInteractor
import com.nix.summer.finall.core.interactors.TakeMoneyInteractor
import com.nix.summer.finall.core.interactors.ShowResourcesInteractor

class MainPresenter(var buyCoffeeInteractor: BuyCoffeeInteractor,
                    var fillResourcesInteractor: FillResourcesInteractor,
                    var takeMoneyInteractor: TakeMoneyInteractor,
                    var showResourcesInteractor: ShowResourcesInteractor) : Contract.Presenter {

    private var view: Contract.View? = null

    override fun attach(view: Contract.View) {
        this.view = view
    }

    override fun detach() {
        this.view = null
    }

    fun start() {
        view?.showResources(showResourcesInteractor())
    }

    fun takeCommand(command: String){
        when (command) {
            "ESPRESSO" -> {
                view?.setStatus(buyCoffeeInteractor(Coffee.ESPRESSO))
            }
            "LATTE" -> {
                view?.setStatus(buyCoffeeInteractor(Coffee.LATTE))
            }
            "CAPPUCCINO" -> {
                view?.setStatus(buyCoffeeInteractor(Coffee.CAPPUCCINO))
            }
            "take" -> {
                view?.takeMoney(takeMoneyInteractor())
            }
            "remaining" -> {
                view?.showResources(showResourcesInteractor())
            }
            else -> println(" ")
        }
        view?.showResources(showResourcesInteractor())
    }

    fun fillResources(resources: Resources) {
        fillResourcesInteractor(resources)
        view?.showResources(showResourcesInteractor())
    }

}
