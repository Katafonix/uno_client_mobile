package com.example.uno.core.presentation.navigation

import androidx.activity.OnBackPressedCallback
import androidx.annotation.IdRes
import androidx.fragment.app.FragmentActivity
import com.example.uno.core.common.ActivityRequired
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GlobalNavComponentRouter @Inject constructor(
    private val destinationsProvider: DestinationsProvider,
) : ActivityRequired {

    private var activity: FragmentActivity? = null
    private var started = false
    private var completelyDestroyed = true
    private val commands = mutableListOf<() -> Unit>()

    private val onBackPressHandlers = LinkedHashSet<() -> Boolean>()

    override fun onCreated(activity: FragmentActivity) {
        this.completelyDestroyed = false
        this.activity = activity
        setupBackHandlers()
    }

    override fun onStarted() {
        started = true
        commands.forEach { it() }
        commands.clear()
    }

    override fun onStopped() {
        started = false
    }

    override fun onDestroyed() {
        if (activity?.isFinishing == true) {
            commands.clear()
            completelyDestroyed = true
        }
    }

    fun registerBackHandler(scope: CoroutineScope, handler: () -> Boolean) {
        scope.launch {
            suspendCancellableCoroutine { continuation ->
                onBackPressHandlers.add(handler)
                continuation.invokeOnCancellation {
                    onBackPressHandlers.remove(handler)
                }
            }
        }
    }

    fun pop() = invoke {
        requireRealRouter().pop()
    }

    fun restart() = invoke {
        requireRealRouter().switchToStack(destinationsProvider.provideStartDestinationId())
    }

    fun launch(@IdRes destinationId: Int, args: java.io.Serializable? = null) = invoke {
        requireRealRouter().launch(destinationId, args)
    }

    private fun setupBackHandlers() {
        requireActivity().onBackPressedDispatcher.addCallback(
            requireActivity(),
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (requireRealRouter().isDialog()) {
                        processAsUsual()
                        return
                    }
                    onBackPressHandlers.reversed().forEach { handler ->
                        if (handler.invoke()) {
                            return
                        }
                    }
                    processAsUsual()
                }

                private fun processAsUsual() {
                    isEnabled = false
                    requireActivity().onBackPressedDispatcher.onBackPressed()
                    isEnabled = true
                }
            })
    }

    private fun invoke(command: () -> Unit) {
        if (started) {
            command()
        } else if (!completelyDestroyed) {
            commands.add(command)
        }
    }

    private fun requireRealRouter(): NavComponentRouter {
        return (activity as RouterHolder).requireRouter()
    }

    private fun requireActivity(): FragmentActivity {
        return activity!!
    }
}