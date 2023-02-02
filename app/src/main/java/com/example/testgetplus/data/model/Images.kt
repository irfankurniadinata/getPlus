package com.example.testgetplus.data.model

import com.google.gson.annotations.SerializedName

class Images {
    @SerializedName("Feature")
    var feature: Images? = null
    @SerializedName("ImageURL")
    var imageUrl: String? = null
    @SerializedName("ThumbnailURL")
    var thumbnailUrl: String? = null
}