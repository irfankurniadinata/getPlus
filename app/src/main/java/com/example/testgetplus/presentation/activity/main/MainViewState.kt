package com.example.testgetplus.presentation.activity.main

import com.example.testgetplus.data.model.Home

sealed class MainViewState {
    object Init : MainViewState()
    data class Progress(val isLoading: Boolean): MainViewState()
    data class ShowMessage(val msg: String): MainViewState()
    data class ShowHomepage(val homepage: Home?): MainViewState()
}
