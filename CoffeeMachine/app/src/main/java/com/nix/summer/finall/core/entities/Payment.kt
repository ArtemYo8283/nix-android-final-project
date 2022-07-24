package com.nix.summer.finall.core.entities

data class Payment(
    val id: Long? = null,
    val amount: Double,
    val currency: String
)
