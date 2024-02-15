package com.example.uno.core.presentation.navigation

import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.uno.core.presentation.ARG_SCREEN
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject


class NavComponentRouter @AssistedInject constructor(
    @Assisted @IdRes private val fragmentContainerId: Int,
    private val destinationsProvider: DestinationsProvider,
    private val navigationModeHolder: NavigationModeHolder,
    private val activity: FragmentActivity,
) {

    private var currentStartDestination = 0
    private var navController: NavController? = null
    private var fragmentDialogs: Int = 0

    private val destinationListeners = mutableSetOf<() -> Unit>()

    private val fragmentListener = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentViewCreated(
            fm: FragmentManager,
            f: Fragment,
            v: View,
            savedInstanceState: Bundle?
        ) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState)
            if (f is NavHostFragment) return
            val currentNavController = f.findNavController()
            onNavControllerActivated(currentNavController)
            destinationListeners.forEach { it() }
        }

        override fun onFragmentStarted(fm: FragmentManager, f: Fragment) {
            super.onFragmentStarted(fm, f)
            if (f is DialogFragment) fragmentDialogs++
        }

        override fun onFragmentStopped(fm: FragmentManager, f: Fragment) {
            super.onFragmentStopped(fm, f)
            if (f is DialogFragment) fragmentDialogs--
        }
    }

    fun onCreate() {
        activity.supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentListener, true)
    }

    fun onDestroy() {
        activity.supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentListener)
        navController = null
        destinationListeners.clear()
    }

    fun onNavigateUp(): Boolean {
        pop()
        return true
    }

    fun onSaveInstanceState(bundle: Bundle) {
        bundle.putInt(KEY_START_DESTINATION, currentStartDestination)
        bundle.putSerializable(KEY_NAV_MODE, navigationModeHolder.navigationMode)
    }

    fun onRestoreInstanceState(bundle: Bundle) {
        currentStartDestination = bundle.getInt(KEY_START_DESTINATION, 0)
        @Suppress("DEPRECATION")
        navigationModeHolder.navigationMode =
            bundle.getSerializable(KEY_NAV_MODE) as? NavigationMode
                ?: throw IllegalStateException("No state to be restored")
        restoreRoot()
    }

    fun switchToStack(@IdRes initialDestinationId: Int) {
        navigationModeHolder.navigationMode = NavigationMode.Stack
        switchRoot(initialDestinationId)
        currentStartDestination = initialDestinationId
    }

    fun launch(@IdRes destinationId: Int, args: java.io.Serializable? = null) {
        if (args == null) {
            getRootNavController().navigate(resId = destinationId)
        } else {
            getRootNavController().navigate(
                resId = destinationId,
                args = Bundle().apply {
                    putSerializable(ARG_SCREEN, args)
                }
            )
        }
    }

    fun pop() {
        activity.onBackPressedDispatcher.onBackPressed()
    }

    internal fun addDestinationListener(listener: () -> Unit) {
        destinationListeners.add(listener)
    }

    internal fun isDialog(): Boolean {
        return fragmentDialogs > 0
    }

    private fun switchRoot(@IdRes rootDestinationId: Int) {
        if (currentStartDestination == 0) {
            restoreRoot()
        } else {
            getRootNavController().navigate(
                resId = rootDestinationId,
                args = null,
                navOptions {
                    popUpTo(currentStartDestination) {
                        inclusive = true
                    }
                }
            )
        }
    }

    private fun restoreRoot() {
        val graph =
            getRootNavController().navInflater.inflate(destinationsProvider.provideNavigationGraphId())
        graph.setStartDestination(destinationsProvider.provideStartDestinationId())
        getRootNavController().graph = graph
    }

    private fun getRootNavController(): NavController {
        val fragmentManager = activity.supportFragmentManager
        val navHost = fragmentManager.findFragmentById(fragmentContainerId) as NavHostFragment
        return navHost.navController
    }

    private fun onNavControllerActivated(navController: NavController) {
        if (this.navController == navController) return
        this.navController = navController
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @IdRes fragmentContainerId: Int,
        ): NavComponentRouter
    }

    private companion object {
        const val KEY_START_DESTINATION = "startDestination"
        const val KEY_NAV_MODE = "navMode"
    }
}
