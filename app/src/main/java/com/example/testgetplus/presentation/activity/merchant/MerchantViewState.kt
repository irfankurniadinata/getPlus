package com.example.testgetplus.presentation.activity.merchant

import com.example.testgetplus.data.model.Merchant

sealed class MerchantViewState {
    object Init : MerchantViewState()
    data class Progress(val isLoading: Boolean): MerchantViewState()
    data class ShowMessage(val msg: String): MerchantViewState()
    data class ShowMerchant(val merchant: Merchant?): MerchantViewState()
}
