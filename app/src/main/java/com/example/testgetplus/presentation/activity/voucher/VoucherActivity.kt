package com.example.testgetplus.presentation.activity.voucher

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testgetplus.R
import com.example.testgetplus.core.BaseActivity
import com.example.testgetplus.data.model.Voucher
import com.example.testgetplus.data.model.VoucherDetail
import com.example.testgetplus.databinding.ActivityVoucherBinding
import com.example.testgetplus.presentation.adapter.AdapterClickListener
import com.example.testgetplus.presentation.adapter.VoucherAdapter
import com.example.testgetplus.presentation.custom.NavigationView
import com.example.testgetplus.utils.extension.setupLinearLayoutManager
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class VoucherActivity : BaseActivity<ActivityVoucherBinding>() {

    private val viewModel by viewModel<VoucherViewModel>()

    override fun getResLayoutId(): Int = R.layout.activity_voucher

    private val voucherAdapter = VoucherAdapter(arrayListOf()).apply {
        listener = object : AdapterClickListener<VoucherDetail> {
            override fun onItemClick(data: VoucherDetail) {

            }

            override fun onViewClick(view: View, data: VoucherDetail) {

            }

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        initView()
        subscribeState()
    }

    private fun initView() {
        binding.apply {
            data = viewModel
            NavigationView(this@VoucherActivity).setupNavigationWithTitle("Vouchers") {
                onBackPressed()
            }

            setupAdapterVoucher()
            viewModel.getVoucher()
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

    private fun handleFlowState(state: VoucherViewState) {
        when (state) {
            is VoucherViewState.Init -> Unit
            is VoucherViewState.Progress -> onProgress(state.isLoading)
            is VoucherViewState.ShowMessage -> onShowMessage(state.msg)
            is VoucherViewState.ShowVoucher -> onShowVoucher(state.voucher)
        }
    }

    private fun setupAdapterVoucher() {
        binding.apply {
            rvVoucher.adapter = voucherAdapter
            rvVoucher.setupLinearLayoutManager(LinearLayoutManager.VERTICAL)
        }
    }

    private fun onShowVoucher(voucher: Voucher?) {
        voucherAdapter.setData(voucher?.list)
    }

    private fun onShowMessage(message: String) {
        Toast.makeText(this, message, Toast. LENGTH_SHORT).show()
    }

    private fun onProgress(loading: Boolean) {
        if (loading) showProgress() else hideProgress()
    }
}