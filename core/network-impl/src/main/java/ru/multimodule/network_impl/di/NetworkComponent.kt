package ru.multimodule.network_impl.di

import dagger.Component
import ru.multimodule.network_api.NetworkApi
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface NetworkComponent: NetworkApi {

    @Component.Factory
    interface Factory {
        fun create(): NetworkComponent
    }
}