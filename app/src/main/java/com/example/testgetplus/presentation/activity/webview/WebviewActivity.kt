package com.example.testgetplus.presentation.activity.webview


import android.os.Bundle
import com.example.testgetplus.R
import com.example.testgetplus.core.BaseActivity
import com.example.testgetplus.databinding.ActivityWebviewBinding
import com.example.testgetplus.presentation.custom.NavigationView
import com.example.testgetplus.utils.constant.K

class WebviewActivity : BaseActivity<ActivityWebviewBinding>() {

    override fun getResLayoutId(): Int = R.layout.activity_webview

    var header: String? = null
    var url : String? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        initView()
    }

    private fun initView() {
        header = intent.getStringExtra(K.KEY_HEADER)
        url = intent.getStringExtra(K.KEY_URL)
        header?.let {
            NavigationView(this).setupNavigationWithTitle(it) {
                onBackPressed()
            }
        }

        binding.apply {
            webView.settings.javaScriptEnabled = true
            url?.let {
                webView.loadUrl(it)
            }
            setContentView(webView)
        }
    }
}