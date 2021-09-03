package com.github.mathsemilio.stackoverflowquestions.common.baseobservable

abstract class BaseObservable<T> : Observable<T> {

    private val _listeners = mutableSetOf<T>().toHashSet()
    protected val listeners get() = _listeners.toSet()

    override fun addListener(listener: T) {
        _listeners.add(listener)
    }

    override fun removeListener(listener: T) {
        _listeners.remove(listener)
    }

    protected inline fun BaseObservable<T>.notifyListener(
        onNotifyListener: (T) -> Unit
    ) {
        this@BaseObservable.listeners.forEach { listener ->
            onNotifyListener(listener)
        }
    }
}