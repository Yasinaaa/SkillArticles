package ru.skillbranch.sbdelivery.ui.search

import ru.skillbranch.sbdelivery.core.adapter.ProductItemState
import ru.skillbranch.sbdelivery.repository.error.EmptyDishesError

sealed class SearchState(var items: List<ProductItemState>? = null){
    object Loading : SearchState()
    data class Result(var item: List<ProductItemState>): SearchState(item)
    data class Error(val errorDescription: String): SearchState(null)
}

