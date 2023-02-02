package com.example.testgetplus.presentation.activity.main

import androidx.lifecycle.viewModelScope
import com.example.testgetplus.core.BaseViewModel
import com.example.testgetplus.data.model.Home
import com.example.testgetplus.data.model.Layout
import com.example.testgetplus.domain.usecase.GetHomePageUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainViewModel(
    private val getHomePageUseCase: GetHomePageUseCase
): BaseViewModel() {
    private val _state = MutableStateFlow<MainViewState>(MainViewState.Init)
    val state: StateFlow<MainViewState> get() = _state

    fun getHomepage() {
        viewModelScope.launch {
            getHomePageUseCase.execute()
                .onStart { showLoading() }
                .catch { e ->
                    hideLoading()
                    e.message?.let { showMessage(it) }
                }
                .collect { result ->
                    hideLoading()
                    showHomepage(result.data)
                }
        }
    }

    private fun showHomepage(result: Home?) {
        _state.value = MainViewState.ShowHomepage(result)
    }

    private fun showMessage(message: String) {
        _state.value = MainViewState.ShowMessage(message)
    }

    private fun hideLoading() {
        _state.value = MainViewState.Progress(false)
    }

    private fun showLoading() {
        _state.value = MainViewState.Progress(true)
    }
}