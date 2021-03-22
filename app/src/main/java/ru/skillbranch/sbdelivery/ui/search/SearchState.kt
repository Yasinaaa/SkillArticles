package ru.skillbranch.sbdelivery.ui.search

import ru.skillbranch.sbdelivery.core.adapter.ProductItemState

sealed class SearchState(var items: List<ProductItemState>? = null){


    object Loading : SearchState()

//    data class Loading(override val items: List<ProductItemState>): SearchState()

    data class Result(var item: List<ProductItemState>?): SearchState(item)

    data class Error(val errorDescription: String): SearchState(null)

    //object Loading : SearchState()
    //
    //    data class Result(val items: List<ProductItemState>): SearchState()
    //
    //    data class Error(val message: String): SearchState()

}

