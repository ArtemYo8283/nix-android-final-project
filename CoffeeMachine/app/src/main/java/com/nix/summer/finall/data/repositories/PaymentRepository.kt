package com.nix.summer.finall.data.repositories

import com.nix.summer.finall.core.entities.Payment

interface PaymentRepository {

    suspend fun makeNetworkExchange(payment: Payment): Payment

    fun savePayment(payment: Payment)

    fun loadPayment(): Payment?
}
