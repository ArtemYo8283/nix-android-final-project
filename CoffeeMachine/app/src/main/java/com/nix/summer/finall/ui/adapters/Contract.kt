package com.nix.summer.finall.ui.adapters

import com.nix.summer.finall.core.entities.Resources
import com.nix.summer.finall.core.entities.Status

class Contract {

    interface View {
        fun setStatus(status: Status)
        fun showResources(resources: Resources)
        fun takeMoney(money: Double)
        fun showEspressoPrice(str: String)
        fun showLattePrice(str: String)
        fun showCappuccinoPrice(str: String)
        fun showPayment(str: String)
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()
    }
}

