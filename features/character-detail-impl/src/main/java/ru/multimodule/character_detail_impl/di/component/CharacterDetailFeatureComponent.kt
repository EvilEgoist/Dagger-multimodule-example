package ru.multimodule.character_detail_impl.di.component

import dagger.Component
import ru.multimodule.character_detail_impl.di.dependencies.CharacterDetailFeatureDependencies
import ru.multimodule.character_detail_impl.di.modules.RepositoryModule
import ru.multimodule.character_detail_impl.di.modules.UseCaseModule
import ru.multimodule.character_detail_impl.presentation.view.CharacterDetailFragment
import ru.multimodule.dagger.PerFeature

@PerFeature
@Component(
    modules = [RepositoryModule::class, UseCaseModule::class],
    dependencies = [CharacterDetailFeatureDependencies::class]
)
interface CharacterDetailFeatureComponent {

    companion object {

        @Volatile
        var characterDetailFeatureComponent: CharacterDetailFeatureComponent? = null
            private set

        fun initAndGet(deps: CharacterDetailFeatureDependencies): CharacterDetailFeatureComponent? {
            if (characterDetailFeatureComponent == null) {
                synchronized(this) {
                    if (characterDetailFeatureComponent == null) {
                        characterDetailFeatureComponent =
                            DaggerCharacterDetailFeatureComponent.factory().create(deps)
                    }
                }
            }
            return characterDetailFeatureComponent
        }

        fun get(): CharacterDetailFeatureComponent? {
            return if (characterDetailFeatureComponent != null){
                characterDetailFeatureComponent
            } else {
                throw RuntimeException("Trying get ${this::class.simpleName} without initialization")
            }
        }

        fun reset(){
            characterDetailFeatureComponent = null
        }
    }

    @Component.Factory
    interface Factory {

        fun create(deps: CharacterDetailFeatureDependencies): CharacterDetailFeatureComponent
    }

    fun inject(fragment: CharacterDetailFragment)
}