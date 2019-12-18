package com.bt.whide.driver.di

import android.app.Activity
import android.app.Application.ActivityLifecycleCallbacks
import android.os.Bundle


import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector

class AppInjector() {

    // Static Methods
    companion object{
        fun init(letstrackApp: App) {
            letstrackApp.registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
                override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle) {
                    handleActivity(activity)
                }

                override fun onActivityStarted(activity: Activity) { //do nothing
                }

                override fun onActivityResumed(activity: Activity) { //do nothing
                }

                override fun onActivityPaused(activity: Activity) { //do nothing
                }

                override fun onActivityStopped(activity: Activity) { //do nothing
                }

                override fun onActivitySaveInstanceState(
                    activity: Activity,
                    outState: Bundle
                ) { //do nothing
                }

                override fun onActivityDestroyed(activity: Activity) { //do nothing
                }
            })
        }
        private fun handleActivity(activity: Activity) {
            if (activity is HasSupportFragmentInjector) {
                AndroidInjection.inject(activity)
            }
            /*if (activity is FragmentActivity) {
                activity.supportFragmentManager
                    .registerFragmentLifecycleCallbacks(
                        object : FragmentManager.FragmentLifecycleCallbacks() {
                            override fun onFragmentCreated(
                                fm: FragmentManager,
                                f: Fragment,
                                savedInstanceState: Bundle?
                            ) {
                                if (f is Injectable) {
                                    AndroidSupportInjection.inject(f)
                                }
                            }
                        }, true
                    )
            }*/
        }
    }


}