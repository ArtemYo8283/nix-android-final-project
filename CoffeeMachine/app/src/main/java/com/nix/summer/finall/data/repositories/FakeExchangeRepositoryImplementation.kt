package com.nix.summer.finall.data.repositories

import com.nix.summer.finall.core.entities.Payment
import com.nix.summer.finall.data.network.ExchangeServiceAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.nix.summer.finall.data.mappers.NetworkPaymentToPaymentMapper

class FakeExchangeRepositoryImplementation(private val exchangeServiceApi: ExchangeServiceAPI,
                                           private val networkPaymentToPaymentMapper: NetworkPaymentToPaymentMapper) : PaymentRepository {

    override suspend fun makeNetworkExchange(payment: Payment): Payment {
        val networkPayment = withContext(Dispatchers.IO) {
            exchangeServiceApi.exchangeCurrency(
                "USD/${payment.currency}/${payment.amount}"
            )
        }
        return networkPaymentToPaymentMapper.toDomain(networkPayment)
    }

    override fun savePayment(payment: Payment)
    {

    }

    override fun loadPayment(id: Long): Payment?
    {
        return Payment(0.0, "")
    }
}
