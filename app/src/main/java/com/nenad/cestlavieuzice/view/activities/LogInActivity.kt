package com.nenad.cestlavieuzice.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nenad.cestlavieuzice.R
import com.nenad.cestlavieuzice.databinding.ActivityLogInBinding

class LogInActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityLogInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityLogInBinding.inflate(layoutInflater)

        setOnClickListeners()

        setContentView(mBinding.root)
    }

    private fun setOnClickListeners() {
        mBinding.backBtn.setOnClickListener {
            val intent = Intent(this@LogInActivity, IntroScreen::class.java)
            startActivity(intent)
            finish()
        }
        mBinding.here.setOnClickListener {
            val intent = Intent(this@LogInActivity, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}