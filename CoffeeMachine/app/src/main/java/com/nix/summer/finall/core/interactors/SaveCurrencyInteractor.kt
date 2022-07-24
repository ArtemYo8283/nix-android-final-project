package com.nix.summer.finall.core.interactors

import com.nix.summer.finall.core.entities.Payment
import com.nix.summer.finall.data.repositories.PaymentRepository

class SaveCurrencyInteractor(private val repository: PaymentRepository) {

    suspend operator fun invoke(payment: Payment) {
        val exchangedPayment = if (payment.currency != "USD") {
            repository.makeNetworkExchange(payment)
        } else {
            payment
        }
        repository.savePayment(exchangedPayment)
    }
}