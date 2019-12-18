package com.bt.whide.driver.di.component

import com.bt.whide.driver.di.App
import com.bt.whide.driver.di.builder.ActivityBuilder
import com.bt.whide.driver.di.module.AppModule
import com.bt.whide.driver.di.module.PreferenceModule
import com.bt.whide.driver.di.scopes.ApplicationScoped
import com.bt.whide.driver.helpers.AppPrefs
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@ApplicationScoped
@Component(
    modules =
    arrayOf(AndroidSupportInjectionModule::class,
        AppModule::class,
        PreferenceModule::class,
        ActivityBuilder::class)
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