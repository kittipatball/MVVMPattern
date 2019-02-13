package com.clicknext.mvvmpattern.Connection

import com.clicknext.mvvmpattern.Model.ResultGetPromotion
import retrofit2.Call
import retrofit2.http.GET

interface Service {

    @GET("GetPromotion")
    fun callServiceGetPromotion(): Call<ResultGetPromotion>

}