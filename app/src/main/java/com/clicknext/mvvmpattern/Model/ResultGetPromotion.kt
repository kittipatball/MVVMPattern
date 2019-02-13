package com.clicknext.mvvmpattern.Model

import com.google.gson.annotations.SerializedName

class ResultGetPromotion {

    @SerializedName("results")
    private var results: List<Results>? = null

    fun getResults(): List<Results>? {
        return results
    }

    fun setResults(results: List<Results>) {
        this.results = results
    }

    class Results {

        @SerializedName("rnum")
        var rnum: String? = null
        @SerializedName("promotionId")
        var promotionId: String? = null
        @SerializedName("promotionName")
        var promotionName: String? = null
        @SerializedName("promotionDescription")
        var promotionDescription: String? = null
        @SerializedName("imageUrl")
        var imageUrl: String? = null
        @SerializedName("webUrl")
        var webUrl: String? = null
        @SerializedName("inAppView")
        var inAppView: String? = null
        @SerializedName("campaignCode")
        var campaignCode: String? = null
        @SerializedName("campaignName")
        var campaignName: String? = null
    }

}