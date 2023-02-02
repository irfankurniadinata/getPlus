package com.example.testgetplus.presentation.activity.merchant

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testgetplus.R
import com.example.testgetplus.core.BaseActivity
import com.example.testgetplus.data.model.Merchant
import com.example.testgetplus.data.model.MerchantDetail
import com.example.testgetplus.databinding.ActivityMerchantBinding
import com.example.testgetplus.presentation.adapter.AdapterClickListener
import com.example.testgetplus.presentation.adapter.MerchantAdapter
import com.example.testgetplus.presentation.custom.NavigationView
import com.example.testgetplus.utils.extension.setupLinearLayoutManager
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class MerchantActivity : BaseActivity<ActivityMerchantBinding>() {

    private val viewModel by viewModel<MerchantViewModel>()

    override fun getResLayoutId(): Int = R.layout.activity_merchant

    private val merchantAdapter = MerchantAdapter(arrayListOf()).apply {
        listener = object : AdapterClickListener<MerchantDetail> {
            override fun onItemClick(data: MerchantDetail) {

            }

            override fun onViewClick(view: View, data: MerchantDetail) {

            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        initView()
        subscribeState()
    }

    private fun initView() {
        NavigationView(this).setupNavigationWithTitle("Merchants") {
            onBackPressed()
        }
        binding.apply {
            data = viewModel

            setupAdapterMerchant()
        }
        viewModel.getMerchant()
    }

    private fun setupAdapterMerchant() {
        binding.apply {
            rvMerchant.adapter = merchantAdapter
            rvMerchant.setupLinearLayoutManager(LinearLayoutManager.VERTICAL)
        }
    }

    private fun subscribeState() {
        lifecycleScope.launch {
            viewModel.state
                .onEach { state ->
                    handleFlowState(state)
                }
                .launchIn(this)
        }
    }

    private fun handleFlowState(state: MerchantViewState) {
        when (state) {
            is MerchantViewState.Init -> Unit
            is MerchantViewState.Progress -> onProgress(state.isLoading)
            is MerchantViewState.ShowMessage -> onShowMessage(state.msg)
            is MerchantViewState.ShowMerchant -> onShowMerchant(state.merchant)
        }
    }

    private fun onShowMerchant(merchant: Merchant?) {
        merchantAdapter.setData(merchant?.list)
    }

    private fun onShowMessage(message: String) {
        Toast.makeText(this, message, Toast. LENGTH_SHORT).show()
    }

    private fun onProgress(loading: Boolean) {
        if (loading) showProgress() else hideProgress()
    }
}