package com.nix.summer.finall.data.mappers

import com.nix.summer.finall.core.entities.Payment
import com.nix.summer.finall.data.network.NetworkPayment

class NetworkPaymentToPaymentMapper {
    fun toDomain(networkPayment: NetworkPayment): Payment = with(networkPayment) {
        Payment(
            currency = targetCurrency,
            amount = conversionResult.toDouble()
        )
    }
}

