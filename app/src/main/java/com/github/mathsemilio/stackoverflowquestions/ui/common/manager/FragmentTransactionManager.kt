package com.github.mathsemilio.stackoverflowquestions.ui.common.manager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class FragmentTransactionManager(
    private val fragmentManager: FragmentManager,
    private val fragmentContainerManager: FragmentContainerManager
) {
    fun replaceFragmentInContainerWith(fragment: Fragment) {
        fragmentManager.beginTransaction().apply {
            setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right,
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right
            )
            replace(fragmentContainerManager.fragmentContainerId, fragment)
            commitNow()
        }
    }

    fun pushFragmentOntoContainer(fragment: Fragment, stackEntryName: String?) {
        fragmentManager.beginTransaction().apply {
            setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right,
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right
            )
            replace(fragmentContainerManager.fragmentContainerId, fragment)
            addToBackStack(stackEntryName)
            commit()
        }
    }

    fun popCurrentFragment() {
        fragmentManager.popBackStackImmediate()
    }
}