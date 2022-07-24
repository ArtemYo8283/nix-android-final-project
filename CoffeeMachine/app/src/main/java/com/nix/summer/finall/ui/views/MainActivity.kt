package com.nix.summer.finall.ui.views

import androidx.core.content.ContextCompat
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.nix.summer.finall.*
import com.nix.summer.finall.core.entities.Coffee
import com.nix.summer.finall.ui.adapters.Contract
import com.nix.summer.finall.ui.adapters.MainPresenter
import com.nix.summer.finall.core.entities.Resources
import com.nix.summer.finall.core.entities.Status
import com.nix.summer.finall.core.interactors.BuyCoffeeInteractor
import com.nix.summer.finall.core.interactors.FillResourcesInteractor
import com.nix.summer.finall.core.interactors.TakeMoneyInteractor
import com.nix.summer.finall.core.interactors.ShowResourcesInteractor
import com.nix.summer.finall.core.interactors.ExchangeCurrencyInteractor
import com.nix.summer.finall.data.repositories.FakeActionRepositoryImplementation
import com.nix.summer.finall.data.repositories.FakeExchangeRepositoryImplementation
import com.nix.summer.finall.data.mappers.NetworkPaymentToPaymentMapper
import com.nix.summer.finall.data.network.Network

class MainActivity : AppCompatActivity(), Contract.View {
    private var presenter = MainPresenter(
        BuyCoffeeInteractor(FakeActionRepositoryImplementation()),
        FillResourcesInteractor(FakeActionRepositoryImplementation()),
        TakeMoneyInteractor(FakeActionRepositoryImplementation()),
        ShowResourcesInteractor(FakeActionRepositoryImplementation()),
        ExchangeCurrencyInteractor(
            FakeExchangeRepositoryImplementation(
                Network.api,
                NetworkPaymentToPaymentMapper()
            )
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.attach(this)
        presenter.start()
        val fillBtn_click = findViewById(R.id.fillBtn) as Button
        fillBtn_click.setOnClickListener {
            fillResources()
        }

        val moneyBtn_click = findViewById(R.id.moneyBtn) as ImageButton
        moneyBtn_click.setOnClickListener {
            presenter.takeCommand("take")
        }

        val espressoBtn_click = findViewById(R.id.espressoBtn) as ImageButton
        espressoBtn_click.setOnClickListener {
            presenter.takeCommand("ESPRESSO")
        }

        val latteBtn_click = findViewById(R.id.latteBtn) as ImageButton
        latteBtn_click.setOnClickListener {
            presenter.takeCommand("LATTE")
        }

        val cappuccinoBtn_click = findViewById(R.id.cappuccinoBtn) as ImageButton
        cappuccinoBtn_click.setOnClickListener {
            presenter.takeCommand("CAPPUCCINO")
        }

        val switchСurrency_click = findViewById(R.id.switchСurrency) as Switch
        switchСurrency_click.setOnClickListener {
            if(switchСurrency_click.isChecked) {
                presenter.exchangePayment("UAH")
            }
            else {
                presenter.exchangePayment("USD")
            }
        }

        val espressoPrice: TextView  = findViewById(R.id.espressoPrice)
        espressoPrice.text = String.format("%.2f", Coffee.ESPRESSO.money) + " USD"

        val lattePrice: TextView  = findViewById(R.id.lattePrice)
        lattePrice.text = String.format("%.2f", Coffee.LATTE.money) + " USD"

        val cappuccinoPrice: TextView  = findViewById(R.id.cappuccinoPrice)
        cappuccinoPrice.text = String.format("%.2f", Coffee.CAPPUCCINO.money) + " USD"
    }

    override fun setStatus(status: Status)
    {

        val statusText: TextView = findViewById(R.id.statusText)
        if(status == Status.OK) {
            statusText.setTextColor(ContextCompat.getColor(applicationContext, android.R.color.holo_green_light))
        }
        else {
            statusText.setTextColor(ContextCompat.getColor(applicationContext, android.R.color.holo_red_dark))
        }
        statusText.text = status.msg
    }

    fun buttonEffect(button: View) {
        button.setOnTouchListener(OnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    v.background.setColorFilter(Color.argb(50,0,0,0), PorterDuff.Mode.SRC_ATOP)
                    v.invalidate()
                }
                MotionEvent.ACTION_UP -> {
                    v.background.clearColorFilter()
                    v.invalidate()
                }
            }
            false
        })
    }

    override fun showResources(resources: Resources) {
        val waterText: TextView  = findViewById(R.id.waterText)
        waterText.text = resources.water.toString() + " ml."

        val milkText: TextView  = findViewById(R.id.milkText)
        milkText.text = resources.milk.toString() + " ml."

        val coffeeText: TextView  = findViewById(R.id.coffeeText)
        coffeeText.text = resources.coffeeBeans.toString() + " gr."

        val cupsText: TextView  = findViewById(R.id.cupsText)
        cupsText.text = resources.disposableCups.toString() + " pcs."
    }

    override fun takeMoney(money: Double) {
        Toast.makeText(this@MainActivity, "You receive $money grn.", Toast.LENGTH_SHORT).show()
    }

    fun fillResources() {
        val waterInput: EditText = findViewById(R.id.waterInput)
        val milkInput: EditText = findViewById(R.id.milkInput)
        val coffeeInput: EditText = findViewById(R.id.coffeeInput)
        val cupsInput: EditText = findViewById(R.id.cupsInput)
        val resources = Resources(
            Integer.parseInt(waterInput.text.toString()),
            Integer.parseInt(milkInput.text.toString()),
            Integer.parseInt(coffeeInput.text.toString()),
            Integer.parseInt(cupsInput.text.toString())
        )
        waterInput.setText("0")
        milkInput.setText("0")
        coffeeInput.setText("0")
        cupsInput.setText("0")
        presenter.fillResources(resources)
    }

    override fun showEspressoPrice(str: String) {
        val espressoPrice: TextView = findViewById(R.id.espressoPrice)
        espressoPrice.text = str
    }

    override fun showLattePrice(str: String) {
        val lattePrice: TextView = findViewById(R.id.lattePrice)
        lattePrice.text = str
    }

    override fun showCappuccinoPrice(str: String) {
        val cappuccinoPrice: TextView = findViewById(R.id.cappuccinoPrice)
        cappuccinoPrice.text = str
    }

}

