package ru.skillbranch.sbdelivery.core.notifier

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.ReplaySubject
import ru.skillbranch.sbdelivery.core.notifier.event.BasketEvent
import java.util.*


class BasketNotifierImpl : BasketNotifier {

//    val subject = PublishSubject.create<BasketEvent>()
//
//    override fun eventSubscribe(): Observable<BasketEvent> {
//        return subject
//            .cache()
//            .subscribeOn(Schedulers.io())
//    }
//
//    override fun putDishes(dish: BasketEvent.AddDish) {
//        subject.onNext(dish)
//    }

    private val notifier = ReplaySubject.create<BasketEvent>()

    override fun eventSubscribe(): Observable<BasketEvent> {
        return notifier.hide()
    }

    override fun putDishes(dish: BasketEvent.AddDish) {
        notifier.onNext(dish)
    }
}