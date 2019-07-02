package com.app.dr1009.failurestatusdemo

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.dr1009.failurestatus_extension.networkErrorBar
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
                message = { errorMessage(it) }
            )
        })
    }
}

fun errorMessage(throwable: Throwable?): String = when (throwable) {
    is RuntimeException -> "This is RuntimeException"
    is IOException -> "Network Error"
    else -> "Something wrong!"
}