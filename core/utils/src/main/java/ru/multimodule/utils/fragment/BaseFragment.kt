package ru.multimodule.utils.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import ru.multimodule.utils.common.Action
import ru.multimodule.utils.viewmodels.BaseViewModel

abstract class BaseFragment: Fragment() {

    abstract val viewModel : BaseViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initActionStateObservers()
    }

    protected open fun initActionStateObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.action.collect {
                    when (it) {
                        is Action.ShowToast -> {
                            showToast(it.message)
                        }
                    }
                }
            }
        }
    }

    protected open fun showToast(message: String) {
        Toast.makeText(
            requireContext(),
            message,
            Toast.LENGTH_SHORT
        ).show()
    }
}