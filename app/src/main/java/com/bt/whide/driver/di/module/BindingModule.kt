package com.bt.whide.driver.di.module

import com.bt.whide.driver.di.scopes.BindingScoped
import com.bt.whide.driver.global.CustomBindingAdapter

import dagger.Module
import dagger.Provides

@Module
class BindingModule {
    @Provides
    @BindingScoped
    fun provideImageBindingAdapter(): CustomBindingAdapter {
        return CustomBindingAdapter()
    }
}
