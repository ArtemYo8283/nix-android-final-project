package com.nix.summer.finall.core.interactors

import com.nix.summer.finall.core.entities.Data
import com.nix.summer.finall.data.repositories.PaymentRepository

class LoadPaymentInteractor(private val repository: PaymentRepository) {

    operator fun invoke(): String {
        val payment = repository.loadPayment()
        return ("Action saved in DB: ${payment?.amount ?: "None"} " + (payment?.currency ?: "Unknown"))
    }
}