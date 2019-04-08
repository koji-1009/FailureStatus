package com.app.dr1009.failurestatusdemo

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.app.dr1009.failurestatus.networkErrorBar
import com.app.dr1009.failurestatusdemo.databinding.ActivityMainBinding
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(binding.toolbar)

        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.failureStatus.observe(this, Observer { status ->
            status ?: return@Observer

            networkErrorBar(
                anchor = binding.root,
                status = status,
                message = this::errorMessage
            )
        })
    }

    private fun errorMessage(throwable: Throwable?): String {
        when (throwable) {
            is RuntimeException -> "This is RuntimeException"
            is IOException -> "Network Error"
            else -> "Something wrong!"
        }
    }
}
