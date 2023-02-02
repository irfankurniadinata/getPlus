package com.example.testgetplus.presentation.activity.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testgetplus.R
import com.example.testgetplus.core.BaseActivity
import com.example.testgetplus.data.model.Home
import com.example.testgetplus.data.model.Menu
import com.example.testgetplus.data.model.PromoDetail
import com.example.testgetplus.databinding.ActivityMainBinding
import com.example.testgetplus.presentation.activity.merchant.MerchantActivity
import com.example.testgetplus.presentation.activity.voucher.VoucherActivity
import com.example.testgetplus.presentation.activity.webview.WebviewActivity
import com.example.testgetplus.presentation.adapter.AdapterClickListener
import com.example.testgetplus.presentation.adapter.MenuAdapter
import com.example.testgetplus.presentation.adapter.PromoAdapter
import com.example.testgetplus.presentation.custom.NavigationView
import com.example.testgetplus.utils.constant.K
import com.example.testgetplus.utils.extension.setupLinearLayoutManager
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel by viewModel<MainViewModel>()

    override fun getResLayoutId(): Int = R.layout.activity_main

    private val menuAdapter = MenuAdapter(arrayListOf()).apply {
        listener = object : AdapterClickListener<Menu> {
            override fun onItemClick(data: Menu) {
                val intent = if (data.id == "id_vouchers") {
                    Intent(this@MainActivity, VoucherActivity::class.java)
                } else {
                    Intent(this@MainActivity, MerchantActivity::class.java)
                }

                startActivity(intent)
            }

            override fun onViewClick(view: View, data: Menu) {

            }
        }
    }

    private val promoAdapter = PromoAdapter(arrayListOf()).apply {
        listener = object : AdapterClickListener<PromoDetail> {
            override fun onItemClick(data: PromoDetail) {
                val url = Uri.parse(data.url)
                if (url.getQueryParameter("browser").isNullOrEmpty()) {
                    val intent = Intent(this@MainActivity, WebviewActivity::class.java)
                    intent.putExtra(K.KEY_URL, data.url)
                    intent.putExtra(K.KEY_HEADER, "Promo ${data.order}")
                    startActivity(intent)
                } else {
                    val intent = Intent(Intent.ACTION_VIEW, url)
                    startActivity(intent)
                }
            }

            override fun onViewClick(view: View, data: PromoDetail) {

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

            NavigationView(this@MainActivity).setupTitle("GetPlus Indonesia")

            sectionContent.isGone = true
            setupAdapterMenu()
            setupAdapterPromo()
            viewModel.getHomepage()
        }
    }

    private fun setupAdapterPromo() {
        binding.apply {
            rvPromo.adapter = promoAdapter
            rvPromo.setupLinearLayoutManager(LinearLayoutManager.HORIZONTAL)
        }
    }

    private fun setupAdapterMenu() {
        binding.apply {
            rvMenu.adapter = menuAdapter
            rvMenu.setupLinearLayoutManager(LinearLayoutManager.HORIZONTAL)
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

    private fun handleFlowState(state: MainViewState) {
        when (state) {
            is MainViewState.Init -> Unit
            is MainViewState.Progress -> onProgress(state.isLoading)
            is MainViewState.ShowMessage -> onShowMessage(state.msg)
            is MainViewState.ShowHomepage -> onShowHomepage(state.homepage)
        }
    }

    private fun onShowHomepage(homepage: Home?) {
        binding.sectionContent.isVisible = true
        binding.tvSectionTitle.text = homepage?.layout?.promo?.title
        menuAdapter.setData(homepage?.layout?.menu)
        promoAdapter.setData(homepage?.layout?.promo?.data)
    }

    private fun onShowMessage(msg: String) {
        Toast.makeText(this, msg, Toast. LENGTH_SHORT).show()
    }

    private fun onProgress(loading: Boolean) {
        if (loading) showProgress() else hideProgress()
    }
}