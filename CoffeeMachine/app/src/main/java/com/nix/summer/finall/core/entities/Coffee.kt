package com.nix.summer.finall.core.entities

enum class Coffee(val water: Int, val milk: Int, val coffee: Int, val money: Double) {
    ESPRESSO (250, 0, 16, 4.0),
    LATTE (350, 75, 20, 7.0),
    CAPPUCCINO (200, 100, 12, 6.0)
}
