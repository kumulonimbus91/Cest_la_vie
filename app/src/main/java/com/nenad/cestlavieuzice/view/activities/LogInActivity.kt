package com.nenad.cestlavieuzice.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nenad.cestlavieuzice.R
import com.nenad.cestlavieuzice.databinding.ActivityLogInBinding

class LogInActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityLogInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityLogInBinding.inflate(layoutInflater)

        setContentView(mBinding.root)
    }
}