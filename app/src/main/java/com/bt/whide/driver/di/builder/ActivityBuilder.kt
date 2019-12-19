package com.bt.whide.driver.di.builder

import com.bt.whide.driver.ui.onboarding.activity.MainActivity
import com.bt.whide.driver.di.scopes.ActivityScoped
import dagger.Module

import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityBuilder {

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity?
}