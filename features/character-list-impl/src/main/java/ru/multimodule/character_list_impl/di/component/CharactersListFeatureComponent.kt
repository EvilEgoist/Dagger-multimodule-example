package ru.multimodule.character_list_impl.di.component

import dagger.Component
import ru.multimodule.character_list_impl.di.dependencies.CharacterListFeatureDependencies
import ru.multimodule.character_list_impl.di.modules.RepositoryModule
import ru.multimodule.character_list_impl.di.modules.UseCaseModule
import ru.multimodule.character_list_impl.presentation.view.CharactersFragment
import ru.multimodule.dagger.PerFeature

@PerFeature
@Component(
    modules = [RepositoryModule::class, UseCaseModule::class],
    dependencies = [CharacterListFeatureDependencies::class]
)
interface CharactersListFeatureComponent {

    companion object {

        @Volatile
        var charactersListFeatureComponent: CharactersListFeatureComponent? = null
            private set

        fun initAndGet(
            characterListFeatureDependencies: CharacterListFeatureDependencies
        ): CharactersListFeatureComponent? {
            if (charactersListFeatureComponent == null) {
                synchronized(this) {
                    if (charactersListFeatureComponent == null) {
                        charactersListFeatureComponent = DaggerCharactersListFeatureComponent.factory()
                            .create(characterListFeatureDependencies)
                    }
                }
            }
            return charactersListFeatureComponent
        }

        fun get(): CharactersListFeatureComponent? {
            return if (charactersListFeatureComponent != null){
                charactersListFeatureComponent
            } else {
                throw RuntimeException("Trying get ${this::class.simpleName} without initialization")
            }
        }

        fun reset() {
            charactersListFeatureComponent = null
        }
    }

    @Component.Factory
    interface Factory {

        fun create(characterListFeatureDependencies: CharacterListFeatureDependencies): CharactersListFeatureComponent
    }

    fun inject(fragment: CharactersFragment)
}