package com.bt.whide.driver.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bt.whide.driver.di.scopes.ApplicationScoped
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton


@ApplicationScoped
@Suppress("UNCHECKED_CAST")
class WDViewModelFactory @Inject constructor(private val viewModelsMap: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = viewModelsMap[modelClass] ?:
        viewModelsMap.asIterable().firstOrNull {
            modelClass.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException("unknown model class $modelClass")
        return try {
            creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

}