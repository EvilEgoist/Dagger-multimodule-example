package ru.multimodule.database_impl.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.multimodule.database_impl.CharactersDao
import javax.inject.Singleton

@Singleton
@Component(modules = [DaosModule::class, DatabaseModule::class])
interface DatabaseComponent {

    fun dao(): CharactersDao

    companion object {

        @Volatile
        private var databaseComponent: DatabaseComponent? = null

        fun initAndGet(appContext: Context): DatabaseComponent? {
            if (databaseComponent == null) {
                synchronized(this) {
                    if (databaseComponent == null) {
                        databaseComponent = DaggerDatabaseComponent.factory()
                            .create(appContext)
                    }
                }
            }
            return databaseComponent
        }
    }

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance appContext: Context): DatabaseComponent
    }
}