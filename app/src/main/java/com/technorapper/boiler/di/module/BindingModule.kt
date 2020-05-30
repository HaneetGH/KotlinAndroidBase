package com.technorapper.boiler.di.module

import com.technorapper.boiler.di.scopes.BindingScoped
import com.technorapper.boiler.global.CustomBindingAdapter
import com.squareup.picasso.Picasso

import dagger.Module
import dagger.Provides

@Module
class BindingModule {
    @Provides
    @BindingScoped
    fun provideImageBindingAdapter(picasso: Picasso): CustomBindingAdapter {
        return CustomBindingAdapter(picasso)
    }
}
