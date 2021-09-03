package com.github.mathsemilio.stackoverflowquestions.common.baseobservable

interface Observable<T> {
    fun addListener(listener: T)
    fun removeListener(listener: T)
}