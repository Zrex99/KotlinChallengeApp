package com.ortizzakarie.androidkotlinchallenge.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

/**
 * Abstract Activity which binds [ViewModel] [VM] and [ViewBinding] [VB]
 */
abstract class BaseActivity<VM : ViewModel, VB : ViewBinding> : AppCompatActivity() {

    protected abstract val mViewModel: VM

    protected lateinit var mViewBinding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewBinding = getViewBinding()
    }

    /**
     * Will return the [VB] and assign it to the [mViewBinding]
     */
    abstract fun getViewBinding(): VB
}