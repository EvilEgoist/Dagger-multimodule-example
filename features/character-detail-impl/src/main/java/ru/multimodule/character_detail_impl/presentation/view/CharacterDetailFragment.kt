package ru.multimodule.character_detail_impl.presentation.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.chip.Chip
import kotlinx.coroutines.launch
import ru.multimodule.character_detail_api.CharacterDetailNavigationApi
import ru.multimodule.character_detail_impl.databinding.FragmentCharacterDetailBinding
import ru.multimodule.character_detail_impl.di.component.CharacterDetailFeatureComponent
import ru.multimodule.character_detail_impl.domain.model.CharacterDetailModel
import ru.multimodule.character_detail_impl.domain.usecase.GetCharacterDetailUseCase
import ru.multimodule.character_detail_impl.presentation.view_models.CharacterDetailViewModel
import ru.multimodule.utils.fragment.BaseFragment
import ru.multimodule.utils.viewmodels.BaseViewModel
import ru.multimodule.utils.viewmodels.viewModelCreator
import javax.inject.Inject

class CharacterDetailFragment : BaseFragment() {

    companion object {
        private const val CHARACTER_ID = "character_id"
        private val TAG = CharacterDetailFragment::class.java.simpleName

        @JvmStatic
        fun newInstance(characterId: Int): CharacterDetailFragment {
            return CharacterDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(CHARACTER_ID, characterId)
                }
            }
        }
    }

    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding
        get() = _binding!!

    private var characterId: Int? = null

    @Inject
    lateinit var getCharacterDetailUseCase: GetCharacterDetailUseCase

    @Inject
    lateinit var characterDetailNavigationApi: CharacterDetailNavigationApi

    override val viewModel: CharacterDetailViewModel by viewModelCreator {
        CharacterDetailViewModel(getCharacterDetailUseCase)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        CharacterDetailFeatureComponent.characterDetailFeatureComponent?.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            characterId = it.getInt(CHARACTER_ID)
            Log.d(TAG, "onCreate: char id $characterId")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated: started fragment with id${characterId}")
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        characterId?.let { viewModel.getCharacter(it) }
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.characterDetailUiStateFlow
                .flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collect { uiState ->
                    Log.d(TAG, "initObservers: ${uiState}")
                    if (uiState.isLoading) {

                    }
                    if (uiState.errorMsg.isNotEmpty()) {
                        Log.e(TAG, "UiState is Error because of ${uiState.errorMsg}")
                    }
                    if (uiState.characterDetail != null) {
                        setUpViews(uiState.characterDetail)
                    }
                }

        }
    }

    private fun setUpViews(characterDetail: CharacterDetailModel) {
        with(binding) {
            characterDetailImage.apply {
                Glide.with(this)
                    .load(characterDetail.image)
                    .into(this)
            }
            characterNameTV.text = characterDetail.name
            addChipsDetailInfo(characterDetail)
        }
    }

    private fun addChipsDetailInfo(characterDetail: CharacterDetailModel){
        with(binding){

            val fieldsDetailsList = characterDetail.extractCharacterInfoIntoList()

            for (i in fieldsDetailsList){
                val chip = Chip(characterDetailFieldsChipGroup.context)
                chip.text = i
                characterDetailFieldsChipGroup.addView(chip)
            }
        }
    }

    override fun onPause() {
        if (isRemoving) {
            if (characterDetailNavigationApi.isClosed(this)) {
                CharacterDetailFeatureComponent.reset()
            }
        }
        super.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

fun CharacterDetailModel.extractCharacterInfoIntoList(): List<String>{
    val resultList = mutableListOf<String>()
    resultList.add(this.species)
    resultList.add(this.gender)
    resultList.add(this.status)

    return resultList
}