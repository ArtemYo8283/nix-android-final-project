package com.nix.summer.finall.data.mappers


import com.nix.summer.finall.core.entities.Payment
import com.nix.summer.finall.data.database.DatabasePayment

class PaymentToDatabasePaymentMapper {

    fun toData(payment: Payment): DatabasePayment = with(payment) {
        DatabasePayment(
            id = id,
            currency = currency,
            amount = amount
        )
    }
}
