package com.nix.summer.finall.adapters

import com.nix.summer.finall.core.entities.Resources
import com.nix.summer.finall.core.entities.Status

class Contract {

    interface View {
        fun setStatus(status: Status)
        fun showResources(resources: Resources)
        fun takeMoney(money: Int)
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()
    }
}

