package com.skyeng.test.navigation

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.skyeng.test.R

/**
 * Created on 12.07.2020 by Andrey Voloshin.
 */
class Navigation {
    companion object {

        fun showFragmentRoot(fragmentManager: FragmentManager, fragment: Fragment) {
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            val transaction = fragmentManager.beginTransaction()
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            transaction.replace(R.id.mainContainer, fragment)
            transaction.commit()
        }

        fun showFragmentBack(fragmentManager: FragmentManager, fragment: Fragment) {
            val transaction = fragmentManager.beginTransaction()
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            transaction.replace(R.id.mainContainer, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        fun closeFragment(fragmentManager: FragmentManager, fragment: Fragment) {
            val transaction = fragmentManager.beginTransaction()
            transaction.remove(fragment)
            transaction.commit()
        }
    }
}