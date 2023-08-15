package ru.multimodule.navigation_impl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import ru.multimodule.character_detail_impl.presentation.view.CharacterDetailFragment
import ru.multimodule.character_list_impl.presentation.view.CharactersFragment
import ru.multimodule.navigation_impl.databinding.ActivityMainBinding
import ru.multimodule.navigation_impl.di.CoreNavigationComponent
import ru.multimodule.navigation_impl.di.FeatureInjectorProxy

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null

    private val binding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        CoreNavigationComponent.initAndGet()?.inject(this)
        restoreComponents()
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavigation()
    }

    private fun initNavigation() {
        when {
            FeatureInjectorProxy.isFirstAppLaunch -> {
                navigateToCharactersList()
            }

            supportFragmentManager.backStackEntryCount == 0 -> {
                navigateToCharactersList()
            }
        }
    }

    private fun navigateToCharactersList() {
        FeatureInjectorProxy.initChararactersListFeatureDi(this.applicationContext)
        val newFragment = CharactersFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fragmentContainerVIew,
                newFragment as Fragment,
                CharactersFragment::class.java.simpleName
            )
            .addToBackStack(CharactersFragment::class.java.simpleName)
            .commit()
        FeatureInjectorProxy.isFirstAppLaunch = false
    }

    private fun restoreComponents() {
        FeatureInjectorProxy.initChararactersListFeatureDi(this.applicationContext)
        if (!FeatureInjectorProxy.isFirstAppLaunch) {
            for (i in 0 until supportFragmentManager.backStackEntryCount) {
                val entry = supportFragmentManager.getBackStackEntryAt(i)
                entry.name?.let {
                    when (it) {
                        CharacterDetailFragment::class.java.simpleName ->
                            FeatureInjectorProxy.initCharacterDetailFeatureDi(this.applicationContext)
                    }
                }
            }
        }
    }

    override fun onBackPressed() {
        when (supportFragmentManager.backStackEntryCount){
            1 -> {
                finish()
            }
            else->{
                super.onBackPressed()
            }
        }
    }
}