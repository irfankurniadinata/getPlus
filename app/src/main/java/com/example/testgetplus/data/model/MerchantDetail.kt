package com.example.testgetplus.data.model

import com.google.gson.annotations.SerializedName

class MerchantDetail {
    @SerializedName("ID")
    var id: String? = null
    @SerializedName("RSN")
    var rsn: String? = null
    @SerializedName("Name")
    var name: String? = null
    @SerializedName("DisplayValue")
    var displayValue: String? = null
    @SerializedName("URL")
    var url: String? = null
    @SerializedName("WebsiteProfile")
    var websiteProfile: String? = null
    @SerializedName("PartnerCategory")
    var partnerCategory: MerchantDetail? = null
    @SerializedName("PartnerCategoryID")
    var partnerCategoryID: String? = null
    @SerializedName("Images")
    var images: Images? = null
}
