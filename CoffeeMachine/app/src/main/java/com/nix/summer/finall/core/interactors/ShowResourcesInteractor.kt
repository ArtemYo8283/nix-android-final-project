package com.nix.summer.finall.core.interactors

import com.nix.summer.finall.core.entities.Resources
import com.nix.summer.finall.core.entities.Coffee
import com.nix.summer.finall.core.entities.Status
import com.nix.summer.finall.data.repositories.ActionRepository

class ShowResourcesInteractor(private val repository: ActionRepository) {
    operator fun invoke(): Resources = repository.show()
}
