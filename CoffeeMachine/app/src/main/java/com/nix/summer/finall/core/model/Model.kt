package com.nix.summer.finall.core.model
import android.view.View
import com.nix.summer.finall.core.entities.Resources
import com.nix.summer.finall.core.entities.Coffee
import com.nix.summer.finall.core.entities.Status

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
