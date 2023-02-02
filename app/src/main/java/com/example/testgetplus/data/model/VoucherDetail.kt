package com.example.testgetplus.data.model

import com.google.gson.annotations.SerializedName

class VoucherDetail {
    @SerializedName("RSN")
    var rsn: String? = null
    @SerializedName("DisplayValue")
    var displayValue: String? = null
    @SerializedName("Status")
    var status: String? = null
    @SerializedName("ValidFrom")
    var validFrom: String? = null
    @SerializedName("ValidUntil")
    var validUntil: String? = null
    @SerializedName("VoucherCode")
    var voucherCode: String? = null
    @SerializedName("VoucherValue")
    var voucherValue: Long? = null
    @SerializedName("AuthenticationRequired")
    var authenticationRequired: Boolean? = false
    @SerializedName("VoucherURL")
    var voucherURL: String? = null
    @SerializedName("URLOnly")
    var urlOnly: String? = null
    @SerializedName("IsPendingTransfer")
    var isPendingTransfer: Boolean? = false
    @SerializedName("ReferralCode")
    var referralCode: String? = null
    @SerializedName("ReferralExpiry")
    var referralExpiry: Boolean? = false
    @SerializedName("ScanToUse")
    var scanToUse: Boolean? = false
    @SerializedName("Images")
    var images: Images? = null
}
