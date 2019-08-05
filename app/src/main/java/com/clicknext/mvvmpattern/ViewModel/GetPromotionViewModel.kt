package com.clicknext.mvvmpattern.ViewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.clicknext.mvvmpattern.Connection.CallBackErrorListener
import com.clicknext.mvvmpattern.Connection.RetrofitService
import com.clicknext.mvvmpattern.Connection.Service
import com.clicknext.mvvmpattern.Model.ResultGetPromotion
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.internal.util.HalfSerializer.onComplete
import io.reactivex.plugins.RxJavaPlugins.onSubscribe
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Response

class GetPromotionViewModel : ViewModel() {

    var mResultGetPromotionMutableLiveData = MutableLiveData<ResultGetPromotion>()
    private var subscription: Disposable? = null
    private var mViewModel: CallBackErrorListener? = null

    fun callServiceGetPromotion(viewModel: CallBackErrorListener) : MutableLiveData<ResultGetPromotion> {
        mViewModel = viewModel
        val retrofit = RetrofitService().getRetrofit()
        val service = retrofit.create<Service>(Service::class.java)
        val call = service.callServiceGetPromotion()
        call.enqueue(object : retrofit2.Callback<ResultGetPromotion>{
            override fun onFailure(call: Call<ResultGetPromotion>, t: Throwable) {
                mViewModel?.onFailure()
            }

            override fun onResponse(call: Call<ResultGetPromotion>, response: Response<ResultGetPromotion>) {
                if (response.isSuccessful) {
                    subscription = Observable.fromCallable { mResultGetPromotionMutableLiveData }
                        .concatMap {
                            Observable.just(mResultGetPromotionMutableLiveData)
                        }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe { onSubscribe() }
                        .doOnTerminate { onComplete(response) }
                        .subscribe(
                            { onNext() },
                            { onError() }
                        )
                }else if(response.body() != null){
                    mViewModel?.onError("onError")
                }
            }
        })

        return mResultGetPromotionMutableLiveData
    }

    private fun onSubscribe() {

    }

    private fun onComplete(result: Response<ResultGetPromotion>) {
        mResultGetPromotionMutableLiveData.value = result.body()
        subscription!!.dispose()
    }

    private fun onNext() {

    }

    private fun onError() {

    }

    override fun onCleared() {
        super.onCleared()
        if(subscription != null){
            subscription!!.dispose()
        }
    }
}