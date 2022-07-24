package com.nix.summer.finall.ui.adapters

import com.nix.summer.finall.core.entities.Resources
import com.nix.summer.finall.core.entities.Coffee
import com.nix.summer.finall.core.entities.Status
import com.nix.summer.finall.core.entities.Data
import com.nix.summer.finall.core.entities.Payment
import com.nix.summer.finall.core.interactors.BuyCoffeeInteractor
import com.nix.summer.finall.core.interactors.FillResourcesInteractor
import com.nix.summer.finall.core.interactors.TakeMoneyInteractor
import com.nix.summer.finall.core.interactors.ShowResourcesInteractor
import com.nix.summer.finall.core.interactors.ExchangeCurrencyInteractor
import com.nix.summer.finall.core.interactors.LoadPaymentInteractor
import com.nix.summer.finall.core.interactors.SaveCurrencyInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainPresenter(var buyCoffeeInteractor: BuyCoffeeInteractor,
                    var fillResourcesInteractor: FillResourcesInteractor,
                    var takeMoneyInteractor: TakeMoneyInteractor,
                    var showResourcesInteractor: ShowResourcesInteractor,
                    var exchangeCurrencyInteractor: ExchangeCurrencyInteractor,
                    var saveCurrencyInteractor: SaveCurrencyInteractor,
                    var loadPaymentInteractor: LoadPaymentInteractor) : Contract.Presenter, CoroutineScope {

    private var view: Contract.View? = null

    private val job = Job()
    override val coroutineContext: CoroutineContext = job + Dispatchers.Default

    override fun attach(view: Contract.View) {
        this.view = view
    }

    override fun detach() {
        this.view = null
    }

    fun start() {
        view?.showResources(showResourcesInteractor())
    }

    fun takeCommand(command: String, currency: String){
        when (command) {
            "ESPRESSO" -> {
                view?.setStatus(buyCoffeeInteractor(Coffee.ESPRESSO))
                launch {
                    saveCurrencyInteractor(Payment(null, Coffee.ESPRESSO.money, currency))
                }
            }
            "LATTE" -> {
                view?.setStatus(buyCoffeeInteractor(Coffee.LATTE))
                launch {
                    saveCurrencyInteractor(Payment(null, Coffee.LATTE.money, currency))
                }
            }
            "CAPPUCCINO" -> {
                view?.setStatus(buyCoffeeInteractor(Coffee.CAPPUCCINO))
                launch {
                    saveCurrencyInteractor(Payment(null, Coffee.CAPPUCCINO.money, currency))
                }
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

    fun exchangePayment(currency: String) {
        launch {
            var str = exchangeCurrencyInteractor(Payment(null, Coffee.ESPRESSO.money, currency))
            withContext(Dispatchers.Main) {
                view?.showEspressoPrice(str)
            }
            str = exchangeCurrencyInteractor(Payment(null, Coffee.LATTE.money, currency))
            withContext(Dispatchers.Main) {
                view?.showLattePrice(str)
            }
            str = exchangeCurrencyInteractor(Payment(null, Coffee.CAPPUCCINO.money, currency))
            withContext(Dispatchers.Main) {
                view?.showCappuccinoPrice(str)
            }
        }
    }

    fun loadPayment() {
        launch {
            val answer = loadPaymentInteractor()
            withContext(Dispatchers.Main) {
                view?.showPayment(answer)
            }
        }
    }
}
