package ru.multimodule.navigation_impl.di

import dagger.Component
import ru.multimodule.character_detail_impl.di.component.CharacterDetailFeatureComponent
import ru.multimodule.character_detail_impl.di.component.DaggerCharacterDetailFeatureComponent
import ru.multimodule.navigation_api.NavigationApi
import ru.multimodule.navigation_impl.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [NavigationModule::class])
interface CoreNavigationComponent : NavigationApi {

    companion object {

        @Volatile
        var coreNavigationComponent: CoreNavigationComponent? = null
            private set

        fun initAndGet(): CoreNavigationComponent? {
            if (coreNavigationComponent == null) {
                synchronized(this) {
                    if (coreNavigationComponent == null) {
                        coreNavigationComponent = DaggerCoreNavigationComponent.factory().create()
                    }
                }
            }
            return coreNavigationComponent
        }
    }

    fun inject(activity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(): CoreNavigationComponent
    }
}