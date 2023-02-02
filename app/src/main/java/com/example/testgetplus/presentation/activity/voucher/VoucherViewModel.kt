package com.example.testgetplus.presentation.activity.voucher

import androidx.lifecycle.viewModelScope
import com.example.testgetplus.core.BaseViewModel
import com.example.testgetplus.data.model.Voucher
import com.example.testgetplus.domain.usecase.GetVoucherUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class VoucherViewModel(
    private val getVoucherUseCase: GetVoucherUseCase
): BaseViewModel() {
    private val _state = MutableStateFlow<VoucherViewState>(VoucherViewState.Init)
    val state: StateFlow<VoucherViewState> get() = _state

    fun getVoucher() {
        viewModelScope.launch {
            getVoucherUseCase.execute()
                .onStart { showLoading() }
                .catch { e ->
                    hideLoading()
                    showMessage(e.message.toString())
                }
                .collect { result ->
                    hideLoading()
                    showVoucher(result.data)
                }
        }
    }

    private fun showVoucher(data: Voucher?) {
        _state.value = VoucherViewState.ShowVoucher(data)
    }

    private fun showMessage(message: String) {
        _state.value = VoucherViewState.ShowMessage(message)
    }

    private fun hideLoading() {
        _state.value = VoucherViewState.Progress(false)
    }

    private fun showLoading() {
        _state.value = VoucherViewState.Progress(true)
    }
}