package com.nix.summer.finall

import androidx.core.content.ContextCompat
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    val model = Model()
    val controller = Controller(model)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        controller.attachView(this)
        controller.start()
        val fillBtn_click = findViewById(R.id.fillBtn) as Button
        fillBtn_click.setOnClickListener {
            fillResources()
        }

        val moneyBtn_click = findViewById(R.id.moneyBtn) as ImageButton
        moneyBtn_click.setOnClickListener {
            controller.takeCommand("take")
        }

        val espressoBtn_click = findViewById(R.id.espressoBtn) as ImageButton
        espressoBtn_click.setOnClickListener {
            controller.takeCommand("ESPRESSO")
        }

        val latteBtn_click = findViewById(R.id.latteBtn) as ImageButton
        latteBtn_click.setOnClickListener {
            controller.takeCommand("LATTE")
        }

        val cappuccinoBtn_click = findViewById(R.id.cappuccinoBtn) as ImageButton
        cappuccinoBtn_click.setOnClickListener {
            controller.takeCommand("CAPPUCCINO")
        }
    }

    fun setStatus(status: Status)
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

    fun showResources(resources: Resources) {
        val waterText: TextView  = findViewById(R.id.waterText)
        waterText.text = resources.water.toString() + " ml."

        val milkText: TextView  = findViewById(R.id.milkText)
        milkText.text = resources.milk.toString() + " ml."

        val coffeeText: TextView  = findViewById(R.id.coffeeText)
        coffeeText.text = resources.coffeeBeans.toString() + " gr."

        val cupsText: TextView  = findViewById(R.id.cupsText)
        cupsText.text = resources.disposableCups.toString() + " pcs."
    }

    fun takeMoney(money: Int) {
        Toast.makeText(this@MainActivity, "You receive $money grn.", Toast.LENGTH_SHORT).show()
    }

    fun fillResources() {
        val waterInput: EditText  = findViewById(R.id.waterInput)
        val milkInput: EditText  = findViewById(R.id.milkInput)
        val coffeeInput: EditText  = findViewById(R.id.coffeeInput)
        val cupsInput: EditText  = findViewById(R.id.cupsInput)
        val resources = Resources(Integer.parseInt(waterInput.text.toString()),
            Integer.parseInt(milkInput.text.toString()),
            Integer.parseInt(coffeeInput.text.toString()),
            Integer.parseInt(cupsInput.text.toString()))
        waterInput.setText("0")
        milkInput.setText("0")
        coffeeInput.setText("0")
        cupsInput.setText("0")
        controller.fillResources(resources)
    }
}

