package com.nix.summer.finall.data.mappers

import com.nix.summer.finall.core.entities.Payment
import com.nix.summer.finall.data.database.DatabasePayment

class DatabasePaymentToPaymentMapper {

    fun toDomain(databasePayment: DatabasePayment): Payment = with(databasePayment) {
        Payment(
            id = id,
            currency = currency,
            amount = amount
        )
    }
}