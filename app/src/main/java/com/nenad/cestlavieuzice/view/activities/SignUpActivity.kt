package com.nenad.cestlavieuzice.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nenad.cestlavieuzice.R
import com.nenad.cestlavieuzice.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivitySignUpBinding.inflate(layoutInflater)

        setOnClickListeners()
        setContentView(mBinding.root)
    }

    private fun setOnClickListeners() {
        mBinding.backBtn.setOnClickListener {
            val intent = Intent(this@SignUpActivity, IntroScreen::class.java)
            startActivity(intent)
            finish()
        }
        mBinding.here.setOnClickListener {
            val intent = Intent(this@SignUpActivity, LogInActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}