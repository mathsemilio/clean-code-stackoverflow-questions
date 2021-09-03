package com.github.mathsemilio.stackoverflowquestions.ui.screens.main.view

import android.widget.FrameLayout
import androidx.annotation.StringRes
import com.github.mathsemilio.stackoverflowquestions.ui.common.view.BaseObservableView

abstract class MainActivityView : BaseObservableView<MainActivityView.Listener>() {

    interface Listener {
        fun onToolbarNavigationIconClicked()
    }

    abstract val fragmentContainer: FrameLayout

    abstract fun setToolbarTitle(@StringRes titleId: Int)

    abstract fun showToolbarNavigationIcon()

    abstract fun hideToolbarNavigationIcon()
}