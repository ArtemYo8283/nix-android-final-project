package com.nix.summer.finall.data.repositories

import com.nix.summer.finall.core.entities.Resources
import com.nix.summer.finall.core.entities.Coffee
import com.nix.summer.finall.core.entities.Status

class FakeActionRepositoryImplementation : ActionRepository {
    object CoffeeMachine {
        var resources = Resources(540, 400 , 120,9)
        var money: Double = 550.0
    }

    override fun buy(type: Coffee): Status{
        if(CoffeeMachine.resources.water - type.water < 0) {
            return Status.WATERERROR
        }
        if (CoffeeMachine.resources.milk - type.milk < 0) {
            return Status.MILKERROR
        }
        if (CoffeeMachine.resources.coffeeBeans - type.coffee < 0) {
            return Status.COFFEEERROR
        }
        if (CoffeeMachine.resources.disposableCups == 0) {
            return Status.CUPERROR
        }
        CoffeeMachine.resources.disposableCups--
        CoffeeMachine.money += type.money
        CoffeeMachine.resources.water -= type.water
        CoffeeMachine.resources.milk -= type.milk
        CoffeeMachine.resources.coffeeBeans -= type.coffee
        return Status.OK
    }

    override fun fill(_resources: Resources) {
        CoffeeMachine.resources.water += _resources.water
        CoffeeMachine.resources.milk += _resources.milk
        CoffeeMachine.resources.coffeeBeans += _resources.coffeeBeans
        CoffeeMachine.resources.disposableCups += _resources.disposableCups
    }

    override fun take(): Double {
        var tmp: Double = CoffeeMachine.money
        CoffeeMachine.money = 0.0
        return tmp
    }

    override fun show(): Resources {
        return CoffeeMachine.resources
    }
}