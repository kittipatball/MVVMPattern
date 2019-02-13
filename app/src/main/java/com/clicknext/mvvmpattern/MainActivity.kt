package com.clicknext.mvvmpattern

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.clicknext.mvvmpattern.CustomView.ProgressDialog
import com.clicknext.mvvmpattern.Model.ResultGetPromotion
import com.clicknext.mvvmpattern.ViewModel.GetPromotionViewModel

class MainActivity : AppCompatActivity() {

    private var mGetPromotionViewModel: GetPromotionViewModel? = null
    private var mProgressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setInitView()
        getPromotion()
    }

    private fun getPromotion() {
        mProgressDialog!!.show()
        mGetPromotionViewModel!!.getPromotion().observe(this@MainActivity
            , Observer<ResultGetPromotion> {
                mProgressDialog!!.dismiss()
                Toast.makeText(this@MainActivity, "Success" , Toast.LENGTH_SHORT).show()
            })
    }

    private fun setInitView() {
        mGetPromotionViewModel = ViewModelProviders.of(this@MainActivity).get(GetPromotionViewModel::class.java)
        mProgressDialog = ProgressDialog(this@MainActivity)
    }
}
