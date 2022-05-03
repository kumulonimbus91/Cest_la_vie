package com.nenad.cestlavieuzice.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nenad.cestlavieuzice.R
import com.nenad.cestlavieuzice.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivitySignUpBinding.inflate(layoutInflater)

        setContentView(mBinding.root)
    }
}