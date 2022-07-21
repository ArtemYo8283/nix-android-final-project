package com.nix.summer.finall
import android.view.View

data class Resources(var water: Int = 0,
                     var milk: Int = 0,
                     var coffeeBeans: Int = 0,
                     var disposableCups: Int = 0)

enum class Coffee(val water: Int, val milk: Int, val coffee: Int, val money: Int) {
    ESPRESSO (250, 0, 16, 4),
    LATTE (350, 75, 20, 7),
    CAPPUCCINO (200, 100, 12, 6)
}

enum class Status(val msg: String) {
    WATERERROR ("Sorry, not enough water!"),
    MILKERROR ("Sorry, not enough milk!"),
    COFFEEERROR ("Sorry, not enough coffee!"),
    CUPERROR ("Sorry, not enough cups!"),
    OK ("Coffee is ready! Go to work!")
}

class Model {

    private var resources = Resources(540, 400 , 120,9)
    private var money: Int = 550

    fun take(): Int {
        var tmp: Int = money
        money = 0
        return tmp
    }

    fun remaining(): Resources {
        return resources
    }

    fun fill(_resources: Resources) {
        resources.water += _resources.water
        resources.milk += _resources.milk
        resources.coffeeBeans += _resources.coffeeBeans
        resources.disposableCups += _resources.disposableCups
    }

    fun buy(type: Coffee): Status {
        if(resources.water - type.water < 0) {
            return Status.WATERERROR
        }
        if (resources.milk - type.milk < 0) {
            return Status.MILKERROR
        }
        if (resources.coffeeBeans - type.coffee < 0) {
            return Status.COFFEEERROR
        }
        if (resources.disposableCups == 0) {
            return Status.CUPERROR
        }
        resources.disposableCups--
        money += type.money
        resources.water -= type.water
        resources.milk -= type.milk
        resources.coffeeBeans -= type.coffee
        return Status.OK
    }
}
