package ru.skillbranch.sbdelivery.core.notifier

import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.AsyncSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import ru.skillbranch.sbdelivery.core.notifier.event.BasketEvent
import java.util.*


class BasketNotifierImpl : BasketNotifier {

    val subject = PublishSubject.create<BasketEvent>()

    override fun eventSubscribe(): Observable<BasketEvent> {
        return subject
            .cache()
            .subscribeOn(Schedulers.io())
    }

    override fun putDishes(dish: BasketEvent.AddDish) {
        subject.onNext(dish)
    }


}