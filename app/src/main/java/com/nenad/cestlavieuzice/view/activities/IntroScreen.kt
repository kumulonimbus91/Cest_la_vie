package com.nenad.cestlavieuzice.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nenad.cestlavieuzice.R
import com.nenad.cestlavieuzice.databinding.ActivityIntroScreenBinding

class IntroScreen : AppCompatActivity() {
    private lateinit var mBinding: ActivityIntroScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityIntroScreenBinding.inflate(layoutInflater)

        setContentView(mBinding.root)
    }
}