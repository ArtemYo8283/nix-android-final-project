package com.nix.summer.finall.data.repositories

import com.nix.summer.finall.core.entities.Resources
import com.nix.summer.finall.core.entities.Coffee
import com.nix.summer.finall.core.entities.Status

interface ActionRepository {
    fun buy(type: Coffee): Status

    fun fill(_resources: Resources)

    fun take(): Int

    fun show(): Resources
}