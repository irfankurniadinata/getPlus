package com.example.testgetplus.presentation.activity.voucher

import com.example.testgetplus.data.model.Voucher

sealed class VoucherViewState {
    object Init : VoucherViewState()
    data class Progress(val isLoading: Boolean): VoucherViewState()
    data class ShowMessage(val msg: String): VoucherViewState()
    data class ShowVoucher(val voucher: Voucher?): VoucherViewState()
}
