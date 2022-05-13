package com.nenad.cestlavieuzice.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.nenad.cestlavieuzice.R
import com.nenad.cestlavieuzice.database.user.User
import com.nenad.cestlavieuzice.databinding.ActivitySignUpBinding
import com.nenad.cestlavieuzice.firebase.Firestore

class SignUpActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivitySignUpBinding.inflate(layoutInflater)

        setOnClickListeners()
        supportActionBar?.hide()
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
        mBinding.btnRegister.setOnClickListener {
            registerUser()
        }
    }
    fun userRegisteredSuccess() {

        Toast.makeText(
            this@SignUpActivity,
            "You have successfully registered.",
            Toast.LENGTH_SHORT
        ).show()

        // Hide the progress dialog
        hideProgressDialog()

        /**
         * Here the new user registered is automatically signed-in so we just sign-out the user from firebase
         * and send him to Intro Screen for Sign-In
         */
        FirebaseAuth.getInstance().signOut()
        // Finish the Sign-Up Screen
        finish()
    }
    private fun registerUser(): Boolean {
        // Here we get the text from editText and trim the space
        val name: String = mBinding.etName.text.toString().trim { it <= ' '  }
        val email: String = mBinding.etEmail.text.toString().trim { it <= ' ' }
        val phoneNum: String = mBinding.etPhone.text.toString().trim { it <= ' ' }
        val password: String = mBinding.etPassword.text.toString().trim { it <= ' ' }
        val passwordConfirmed: String = mBinding.etPasswordConfirm.text.toString().trim { it <= ' ' }

        if (validateForm(name, email, phoneNum, password, passwordConfirmed)) {
            // Show the progress dialog.
            showProgressDialog(resources.getString(R.string.please_wait))
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                    OnCompleteListener<AuthResult> { task ->

                        // If the registration is successfully done
                        if (task.isSuccessful) {

                            // Firebase registered user
                            val firebaseUser: FirebaseUser = task.result!!.user!!
                            // Registered Email
                            val registeredEmail = firebaseUser.email!!

                            val user = User(
                                firebaseUser.uid, name, registeredEmail
                            )

                            // call the registerUser function of FirestoreClass to make an entry in the database.
                            Firestore().registerUser(this@SignUpActivity, user)
                        } else {
                            Toast.makeText(
                                this@SignUpActivity,
                                task.exception!!.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
            return true
        } else {
            return false
        }
    }
    private fun validateForm(name: String, email: String, phoneNum: String, password: String, passwordConfirmed: String): Boolean {
        return when {
            TextUtils.isEmpty(name) -> {
                showErrorSnackBar("Molimo unesite ime.")
                false
            }
            TextUtils.isEmpty(email) -> {
                showErrorSnackBar("Molimo unesite email.")
                false
            }
            TextUtils.isEmpty(password) -> {
                showErrorSnackBar("Molimo unesite šifru.")
                false
            }
            TextUtils.isEmpty(passwordConfirmed) -> {
                showErrorSnackBar("Molimo potvrdite šifru")
                false
            }
            TextUtils.isEmpty(phoneNum) -> {
                showErrorSnackBar("Molimo unesite broj telefona")
                false
            }
            !TextUtils.equals(password, passwordConfirmed) -> {
                showErrorSnackBar("Molimo potvrdite šifru")
                false
            }


            else -> {
                true
            }

        }


    }
    fun showErrorSnackBar(message: String) {
        val snackBar =
            Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
        val snackBarView = snackBar.view
        snackBarView.setBackgroundColor(
            ContextCompat.getColor(
                this@SignUpActivity,
                R.color.snackbar_error_color
            )
        )
        snackBar.show()
    }
    fun showProgressDialog(text: String) {



    }
    fun hideProgressDialog() {

    }
}