package com.clicknext.mvvmpattern

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.clicknext.mvvmpattern.Connection.CallBackErrorListener
import com.clicknext.mvvmpattern.CustomView.ProgressDialog
import com.clicknext.mvvmpattern.Model.ResultGetPromotion
import com.clicknext.mvvmpattern.ViewModel.GetPromotionViewModel

class MainActivity : AppCompatActivity() , CallBackErrorListener {

    private var mGetPromotionViewModel: GetPromotionViewModel? = null
    private var mProgressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setInitView()
        setViewModelProviders()
        setObserver()
        callServiceGetPromotion()
    }

    private fun callServiceGetPromotion() {
        mProgressDialog?.show()
        mGetPromotionViewModel?.callServiceGetPromotion(viewModel = this@MainActivity)
    }

    private fun setInitView() {
        mGetPromotionViewModel = ViewModelProviders.of(this@MainActivity).get(GetPromotionViewModel::class.java)
        mProgressDialog = ProgressDialog(this@MainActivity)
    }

    private fun setViewModelProviders() {
        mGetPromotionViewModel = ViewModelProviders.of(this@MainActivity)
            .get(GetPromotionViewModel::class.java)
    }

    private fun setObserver() {
        mGetPromotionViewModel?.mResultGetPromotionMutableLiveData?.observe(this@MainActivity , Observer {
            mProgressDialog!!.dismiss()
            Toast.makeText(this@MainActivity , it?.getResults()!![0].promotionName , Toast.LENGTH_SHORT).show()
        })
    }

    override fun onObserverError() {
        mProgressDialog!!.dismiss()
        Toast.makeText(this@MainActivity , "ObserverError" , Toast.LENGTH_SHORT).show()
    }

    override fun onError(message: String) {
        mProgressDialog!!.dismiss()
        Toast.makeText(this@MainActivity , "onError" , Toast.LENGTH_SHORT).show()
    }

    override fun onFailure() {
        mProgressDialog!!.dismiss()
        Toast.makeText(this@MainActivity , "onFailure" , Toast.LENGTH_SHORT).show()
    }
}
