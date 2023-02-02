package com.example.testgetplus.core

import com.google.gson.annotations.SerializedName

/**
 * This class is used for mapping api response which's have data
 * @param data is response data with generic type of T
 */
data class BaseResponse<T>(
    @SerializedName("data")
    var data: T? = null
)