package com.nix.summer.finall.core.interactors

import com.nix.summer.finall.core.entities.Resources
import com.nix.summer.finall.core.entities.Coffee
import com.nix.summer.finall.core.entities.Status
import com.nix.summer.finall.core.entities.Payment
import com.nix.summer.finall.data.repositories.PaymentRepository

class ExchangeCurrencyInteractor(private val repository: PaymentRepository) {

    suspend operator fun invoke(payment: Payment): String {
        val exchangedPayment = if (payment.currency != "USD") {
            repository.makeNetworkExchange(payment)
        } else {
            payment
        }

        return with(exchangedPayment) {"${String.format("%.2f", amount)} $currency" }
    }
}