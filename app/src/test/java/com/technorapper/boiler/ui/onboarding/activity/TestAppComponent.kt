package com.technorapper.boiler.ui.onboarding.activity

import com.technorapper.boiler.application.App
import com.technorapper.boiler.di.builder.ActivityBuilder
import com.technorapper.boiler.di.builder.FragmentBuilder
import com.technorapper.boiler.di.component.AppComponent
import com.technorapper.boiler.di.module.AppModule
import com.technorapper.boiler.di.module.DataBaseModule
import com.technorapper.boiler.di.module.NetworkModule
import com.technorapper.boiler.di.module.PreferenceModule
import com.technorapper.boiler.di.scopes.ApplicationScoped
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@ApplicationScoped
@Component(
    modules = [AndroidSupportInjectionModule::class,
        AppModule::class,
        PreferenceModule::class,
        DataBaseModule::class,
        NetworkModule::class,
        ActivityBuilder::class,
        FragmentBuilder::class
    ]
)
interface TestAppComponent : AndroidInjector<DaggerApplication> {
    fun into(appRepositoryTest: MainActivityDaggerTest)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder

        fun appModule(appModule: AppModule): Builder
        fun appPrefs(appPref: PreferenceModule): Builder
        fun build(): TestAppComponent
    }
}