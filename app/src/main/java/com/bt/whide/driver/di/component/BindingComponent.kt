package com.bt.whide.driver.di.component
import androidx.databinding.DataBindingComponent
import com.bt.whide.driver.di.module.BindingModule
import com.bt.whide.driver.di.scopes.BindingScoped
import com.bt.whide.driver.global.CustomBindingAdapter
import com.squareup.picasso.Picasso
import dagger.BindsInstance
import dagger.Component

@BindingScoped
@Component(modules = arrayOf(BindingModule::class))
interface BindingComponent : DataBindingComponent {
    fun getImageBindingAdapter(): CustomBindingAdapter?

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun picasso(picasso: Picasso): Builder
        fun bindingModule(bindingModule: BindingModule): Builder
        fun build(): BindingComponent
    }
}