package com.clicknext.mvvmpattern.Connection

interface CallBackErrorListener {

    fun onObserverError()
    fun onError(message: String)
    fun onFailure()

}