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
        /**
         * RNUM : 1
         * promotionId : 50
         * promotionName : ประมูลรถที่ใช่ในราคาที่ชอบ ขอไฟแนนซ์รถยนต์ได้ทันที
         * promotionDescription : ศูนย์ประมูลรถยนต์ธนาคารเกียรตินาคินแหล่งประมูลขนาดใหญ่ ที่มีรถให้เลือกมากมาย
         * imageUrl : https://kkauto.kiatnakin.co.th/KKBackoffice/Images/promotion/169236941514123117287351307692120250576316918671.jpg
         * webUrl : http://www.kiatnakin.co.th/th/carauction/calendar
         * inAppView :
         * campaignCode :
         * campaignName :
         */

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