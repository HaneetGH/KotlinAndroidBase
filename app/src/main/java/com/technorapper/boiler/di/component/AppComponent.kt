package com.technorapper.boiler.di.component


import com.technorapper.boiler.application.App
import com.technorapper.boiler.di.builder.ActivityBuilder
import com.technorapper.boiler.di.builder.FragmentBuilder
import com.technorapper.boiler.di.module.*
import com.technorapper.boiler.di.scopes.ApplicationScoped
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