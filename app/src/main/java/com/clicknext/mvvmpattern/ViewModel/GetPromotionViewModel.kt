package com.clicknext.mvvmpattern.ViewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.clicknext.mvvmpattern.Connection.RetrofitService
import com.clicknext.mvvmpattern.Connection.Service
import com.clicknext.mvvmpattern.Model.ResultGetPromotion
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Response

class GetPromotionViewModel : ViewModel() {

    private var mResultGetPromotionMutableLiveData = MutableLiveData<ResultGetPromotion>()

    private fun callServiceGetPromotion() {
        val retrofit = RetrofitService().getRetrofit()
        val service = retrofit.create<Service>(Service::class.java)
        val call = service.callServiceGetPromotion()
        call.enqueue(object : retrofit2.Callback<ResultGetPromotion>{
            override fun onFailure(call: Call<ResultGetPromotion>, t: Throwable) {

            }

            override fun onResponse(call: Call<ResultGetPromotion>, response: Response<ResultGetPromotion>) {
                Observable.just<ResultGetPromotion>(response.body()!!)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(subscribeData())
            }
        })
    }

    private fun subscribeData(): Observer<ResultGetPromotion> {
        return object : Observer<ResultGetPromotion> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(resultGetPromotion: ResultGetPromotion) {
                mResultGetPromotionMutableLiveData.setValue(resultGetPromotion)
            }

            override fun onError(e: Throwable) {

            }

            override fun onComplete() {

            }
        }
    }

    fun getPromotion(): MutableLiveData<ResultGetPromotion> {
        callServiceGetPromotion()
        return mResultGetPromotionMutableLiveData
    }
}