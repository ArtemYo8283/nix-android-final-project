package com.nix.summer.finall.core.entities

enum class Status(val msg: String) {
    WATERERROR ("Sorry, not enough water!"),
    MILKERROR ("Sorry, not enough milk!"),
    COFFEEERROR ("Sorry, not enough coffee!"),
    CUPERROR ("Sorry, not enough cups!"),
    OK ("Coffee is ready! Go to work!")
}
