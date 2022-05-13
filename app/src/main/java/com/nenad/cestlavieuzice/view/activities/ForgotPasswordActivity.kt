package com.nenad.cestlavieuzice.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.nenad.cestlavieuzice.databinding.ActivityForgotPasswordBinding

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityForgotPasswordBinding.inflate(layoutInflater)

        mBinding.sendBtn.setOnClickListener {
            val email: String = mBinding.etEmail.text.toString().trim { it <= ' ' }
            if (email.isEmpty()) {
                Toast.makeText(this,"Upišite email", Toast.LENGTH_SHORT).show()
            } else {
                FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener{ task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this,"Email je poslat", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this,task.exception!!.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }


        }
        supportActionBar?.hide()

        mBinding.backBtn.setOnClickListener {
            onBackPressed()
        }



        setContentView(mBinding.root)
    }
}