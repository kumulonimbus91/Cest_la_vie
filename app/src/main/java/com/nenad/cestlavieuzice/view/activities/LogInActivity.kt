package com.nenad.cestlavieuzice.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.nenad.cestlavieuzice.R
import com.nenad.cestlavieuzice.database.user.User
import com.nenad.cestlavieuzice.databinding.ActivityLogInBinding
import com.nenad.cestlavieuzice.firebase.Firestore

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
        mBinding.loginBtn.setOnClickListener {
            signInRegisteredUser()
        }
    }
    private fun signInRegisteredUser() {
        // Here we get the text from editText and trim the space
        val email: String = mBinding.etEmail.text.toString().trim { it <= ' ' }
        val password: String = mBinding.etPassword.text.toString().trim { it <= ' ' }

        if (validateForm(email, password)) {
            // Show the progress dialog.
            //showProgressDialog(resources.getString(R.string.please_wait))

            // Sign-In using FirebaseAuth
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Calling the FirestoreClass signInUser function to get the data of user from database.
                        Firestore().loadUserData(this@LogInActivity)
                    } else {
                        Toast.makeText(
                            this@LogInActivity,
                            task.exception!!.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
    }
    /**
     * A function to get the user details from the firestore database after authentication.
     */
    fun signInSuccess(user: User) {

        // hideProgressDialog()

        startActivity(Intent(this@LogInActivity, MainActivity::class.java))
        this.finish()
    }
    private fun validateForm(email: String, password: String): Boolean {
        return if (TextUtils.isEmpty(email)) {
            showErrorSnackBar("Please enter email.")
            false
        } else if (TextUtils.isEmpty(password)) {
            showErrorSnackBar("Please enter password.")
            false
        } else {
            true
        }
    }

    fun showErrorSnackBar(message: String) {
        val snackBar =
            Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
        val snackBarView = snackBar.view
        snackBarView.setBackgroundColor(
            ContextCompat.getColor(
                this@LogInActivity,
                R.color.snackbar_error_color
            )
        )
        snackBar.show()
    }
}