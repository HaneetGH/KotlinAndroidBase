package com.technorapper.boiler.di.builder

import com.technorapper.boiler.ui.onboarding.activity.MainActivity
import com.technorapper.boiler.di.scopes.ActivityScoped
import dagger.Module

import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityBuilder {

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity?

}