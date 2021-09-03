package com.github.mathsemilio.stackoverflowquestions.ui.common.view

import com.github.mathsemilio.stackoverflowquestions.common.baseobservable.Observable

abstract class BaseObservableView<T> : Observable<T>, BaseView() {

    private val _listeners = mutableSetOf<T>()
    protected val listeners get() = _listeners.toSet()

    override fun addListener(listener: T) {
        _listeners.add(listener)
    }

    override fun removeListener(listener: T) {
        _listeners.remove(listener)
    }

    protected inline fun BaseObservableView<T>.notifyListener(
        onNotifyListener: (T) -> Unit
    ) {
        this@BaseObservableView.listeners.forEach { listener ->
            onNotifyListener(listener)
        }
    }
}