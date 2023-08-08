package ru.multimodule.character_list_impl.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import ru.multimodule.character_list_api.CharactersListNavigationApi
import ru.multimodule.character_list_impl.R
import ru.multimodule.character_list_impl.databinding.FragmentCharactersBinding
import ru.multimodule.character_list_impl.di.component.CharactersListFeatureComponent
import ru.multimodule.character_list_impl.domain.usecase.GetCharactersUseCase
import ru.multimodule.character_list_impl.presentation.uistate.CharactersListUIState
import ru.multimodule.character_list_impl.presentation.view.adapters.CharactersRVAdapter
import ru.multimodule.character_list_impl.presentation.view.viewholders.CharactersListItemViewHolder
import ru.multimodule.character_list_impl.presentation.view.viewholders.ViewHolderFactory
import ru.multimodule.character_list_impl.presentation.view_models.CharactersViewModel
import ru.multimodule.utils.fragment.BaseFragment
import ru.multimodule.utils.viewmodels.viewModelCreator
import javax.inject.Inject


class CharactersFragment : BaseFragment() {

    private var _binding: FragmentCharactersBinding? = null

    private val binding
        get() = _binding!!

    @Inject
    lateinit var getCharactersUseCase: GetCharactersUseCase

    @Inject
    lateinit var charactersListNavigationApi: CharactersListNavigationApi


    override val viewModel: CharactersViewModel by viewModelCreator {
        CharactersViewModel(
            getCharactersUseCase
        )
    }

    private val charactersAdapter: CharactersRVAdapter by lazy {
        CharactersRVAdapter(
            object : CharactersRVAdapter.Listener {
                override fun onClickCharacter(holder: CharactersListItemViewHolder, characterId: Int) {
                    charactersListNavigationApi.navigateToDetail(this@CharactersFragment, characterId)
                }
            },
            ViewHolderFactory()
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        CharactersListFeatureComponent.charactersListFeatureComponent?.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
    }

    private fun initViews(){
        with(binding){
            charactersRV.apply {
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                adapter = charactersAdapter
            }
        }
    }

    private fun initObservers(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiStateFlow
                .flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collect { uiState ->
                    with(uiState){
                        if (isLoading) {

                        }
                        if (errorMsg.isNotEmpty()){

                        }
                        if (charactersList.isNotEmpty()){
                            charactersAdapter.submitList(charactersList)
                        }
                    }
                }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CharactersFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CharactersFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}