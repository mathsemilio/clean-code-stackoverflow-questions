package com.github.mathsemilio.stackoverflowquestions.common.provider

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

object CoroutineScopeProvider {
    val UIBoundScope get() = CoroutineScope(Dispatchers.Main.immediate)
}