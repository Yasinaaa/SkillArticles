package ru.skillbranch.sbdelivery.domain.filter

import io.reactivex.rxjava3.core.Single
import ru.skillbranch.sbdelivery.domain.entity.DishEntity
import ru.skillbranch.sbdelivery.repository.DishesRepositoryContract
import ru.skillbranch.sbdelivery.repository.error.EmptyDishesError

class CategoriesFilterUseCase(private val repository: DishesRepositoryContract) : CategoriesFilter {

    override fun categoryFilterDishes(categoryId: String): Single<List<DishEntity>> =
        repository.getCachedDishes()
            .map { categories ->
                if (categoryId.isNotEmpty()) {
                    categories.filter {
                        it.categoryId == categoryId
                    }
                }else{
                    categories
                }
            }.doOnSuccess {
                if (it.isEmpty())
                    throw EmptyDishesError()
            }
}