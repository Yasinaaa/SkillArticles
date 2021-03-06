package ru.skillbranch.sbdelivery.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import ru.skillbranch.sbdelivery.core.BaseViewModel
import ru.skillbranch.sbdelivery.domain.SearchUseCase
import ru.skillbranch.sbdelivery.repository.mapper.DishesMapper
import java.util.concurrent.TimeUnit

class SearchViewModel(
    private val useCase: SearchUseCase,
    private val mapper: DishesMapper
) : BaseViewModel() {
    private val action = MutableLiveData<SearchState>()
    val state: LiveData<SearchState>
        get() = action

    fun initState() {
        action.value = SearchState.Loading
        useCase.getDishes()
            .map { dishes -> mapper.mapDtoToState(dishes) }
            .subscribe({
                action.value = SearchState.Result(it)
            }, {
                action.value = SearchState.Error(it.message!!)
            }).track()
    }

    fun setSearchEvent(searchEvent: Observable<String>) {
        searchEvent
            .delay(2, TimeUnit.SECONDS)
            .debounce(800L, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap {
                action.value = SearchState.Loading
                useCase.findDishesByName(it)
            }
            .map { mapper.mapDtoToState(it) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                action.value = SearchState.Result(it)
            }, {
                action.value = SearchState.Error(it.message!!)
            }).track()
    }
}