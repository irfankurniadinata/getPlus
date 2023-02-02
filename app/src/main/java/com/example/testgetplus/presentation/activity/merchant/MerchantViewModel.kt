package com.example.testgetplus.presentation.activity.merchant

import androidx.lifecycle.viewModelScope
import com.example.testgetplus.core.BaseViewModel
import com.example.testgetplus.data.model.Merchant
import com.example.testgetplus.domain.usecase.GetMerchantUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MerchantViewModel(
    private val getMerchantUseCase: GetMerchantUseCase
): BaseViewModel() {
    private val _state = MutableStateFlow<MerchantViewState>(MerchantViewState.Init)
    val state: StateFlow<MerchantViewState> get() = _state

    fun getMerchant() {
        val queryFilter = HashMap<String, Any?>()
        queryFilter["page"] = 1

        viewModelScope.launch {
            getMerchantUseCase.execute(queryFilter)
                .onStart { showLoading() }
                .catch { e ->
                    hideLoading()
                    showMessage(e.message.toString())
                }
                .collect { result ->
                    hideLoading()
                    showMerchant(result.data)
                }
        }
    }

    private fun showMerchant(data: Merchant?) {
        _state.value = MerchantViewState.ShowMerchant(data)
    }

    private fun showMessage(message: String) {
        _state.value = MerchantViewState.ShowMessage(message)
    }

    private fun hideLoading() {
        _state.value = MerchantViewState.Progress(false)
    }

    private fun showLoading() {
        _state.value = MerchantViewState.Progress(true)
    }
}