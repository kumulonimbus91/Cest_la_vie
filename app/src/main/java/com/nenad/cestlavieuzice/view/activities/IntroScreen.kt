package com.nenad.cestlavieuzice.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nenad.cestlavieuzice.R
import com.nenad.cestlavieuzice.databinding.ActivityIntroScreenBinding

class IntroScreen : AppCompatActivity() {

    private lateinit var mBinding: ActivityIntroScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityIntroScreenBinding.inflate(layoutInflater)

        setOnClickListeners()

        setContentView(mBinding.root)
    }

    private fun setOnClickListeners() {
        mBinding.loginbtn.setOnClickListener {
            val intent = Intent (this@IntroScreen, LogInActivity::class.java)
            startActivity(intent)
            finish()
        }
        mBinding.signupbtn.setOnClickListener {
            val intent = Intent (this@IntroScreen, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}