package com.github.mathsemilio.stackoverflowquestions.ui.common

import androidx.fragment.app.Fragment
import com.github.mathsemilio.stackoverflowquestions.common.di.ControllerCompositionRoot
import com.github.mathsemilio.stackoverflowquestions.ui.screens.main.MainActivity

abstract class BaseFragment : Fragment() {

    val compositionRoot by lazy {
        ControllerCompositionRoot((requireActivity() as MainActivity).compositionRoot)
    }
}