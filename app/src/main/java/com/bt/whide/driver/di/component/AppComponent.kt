package com.bt.whide.driver.di.component

import com.bt.whide.driver.application.App
import com.bt.whide.driver.di.builder.ActivityBuilder
import com.bt.whide.driver.di.builder.FragmentBuilder
import com.bt.whide.driver.di.module.*
import com.bt.whide.driver.di.scopes.ApplicationScoped
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@ApplicationScoped
@Component(
    modules =
    arrayOf(
        AndroidSupportInjectionModule::class,
        AppModule::class,
        PreferenceModule::class,
        DataBaseModule::class,
        NetworkModule::class,
        ActivityBuilder::class,
        FragmentBuilder::class
    )
)
interface AppComponent {
    fun inject(app: App)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder

        fun appModule(appModule: AppModule): Builder

        fun build(): AppComponent
    }
}