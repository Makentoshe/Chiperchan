package com.makentoshe.chiperchan.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.makentoshe.chiperchan.AppActivity
import com.makentoshe.chiperchan.di.common.ApplicationScope
import toothpick.Toothpick

class InjectionActivityLifecycleCallbacks(
    private val injectionFragmentLifecycleCallbacks: InjectionFragmentLifecycleCallbacks
) : Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        if (activity !is AppActivity) return
        Toothpick.openScopes(ApplicationScope::class.java).inject(activity)
        activity.supportFragmentManager.registerFragmentLifecycleCallbacks(
            injectionFragmentLifecycleCallbacks, true
        )
    }

    override fun onActivityPaused(activity: Activity) = Unit

    override fun onActivityStarted(activity: Activity) = Unit

    override fun onActivityDestroyed(activity: Activity) = Unit

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) = Unit

    override fun onActivityStopped(activity: Activity) = Unit

    override fun onActivityResumed(activity: Activity) = Unit
}