package ru.multimodule.database_impl.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DaosModule::class, DatabaseModule::class])
interface DatabaseComponent {

    companion object {

        @Volatile
        private var databaseComponent: DatabaseComponent? = null

        fun initAndGet(appContext: Context): DatabaseComponent? {
            return when (databaseComponent){
                null -> {
                    synchronized(this){
                        when (databaseComponent) {
                            null -> {
                                 databaseComponent = DaggerDatabaseComponent.builder()
                                    .appContext(appContext)
                                    .build()
                            }
                        }
                        databaseComponent
                    }
                }
                else -> {
                    databaseComponent
                }
            }
        }
    }

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun appContext(appContext: Context): Builder

        fun build(): DatabaseComponent
    }
}