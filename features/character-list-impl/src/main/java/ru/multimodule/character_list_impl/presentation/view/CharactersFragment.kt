package ru.multimodule.character_list_impl.presentation.view

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
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

    private val swipeRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        viewModel.getCharacters()
    }

    private val charactersAdapter: CharactersRVAdapter by lazy {
        CharactersRVAdapter(
            object : CharactersRVAdapter.Listener {
                override fun onClickCharacter(
                    holder: CharactersListItemViewHolder,
                    characterId: Int
                ) {
                    charactersListNavigationApi.navigateToDetail(
                        this@CharactersFragment,
                        characterId
                    )
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

    override fun onResume() {
        super.onResume()
        with(binding) {
            swipeRefreshLayout.post {
                swipeRefreshListener.onRefresh()
            }
        }
    }

    private fun initViews() {
        with(binding) {
            charactersRV.apply {
                layoutManager = GridLayoutManager(requireContext(), 2)
                adapter = charactersAdapter
                adapter?.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            }
            swipeRefreshLayout.setOnRefreshListener {
                viewModel.getCharacters()
            }
        }
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiStateFlow
                .flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collect { uiState ->
                    with(uiState) {
                        if (isLoading) {
                            binding.swipeRefreshLayout.isRefreshing = true
                        }
                        if (errorMsg.isNotEmpty()) {
                            binding.swipeRefreshLayout.isRefreshing = false
                            Log.e(TAG, "UiState is Error because of $errorMsg")
                        }
                        if (charactersList.isNotEmpty()) {
                            binding.swipeRefreshLayout.isRefreshing = false
                            charactersAdapter.submitList(charactersList)
                        }
                    }
                }
        }
    }

    override fun onPause() {
        if (isRemoving) {
            if (charactersListNavigationApi.isClosed(this)) {
                CharactersListFeatureComponent.reset()
            }
        }
        super.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private val TAG = CharactersFragment::class.java.simpleName
    }
}