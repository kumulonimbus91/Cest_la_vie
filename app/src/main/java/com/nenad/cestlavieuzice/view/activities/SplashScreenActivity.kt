package com.nenad.cestlavieuzice.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.nenad.cestlavieuzice.R
import com.nenad.cestlavieuzice.databinding.ActivitySplashScreenBinding
import com.nenad.cestlavieuzice.firebase.Firestore
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit
@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivitySplashScreenBinding.inflate(layoutInflater)

        supportActionBar?.hide()

        val scheduledExecutorService = Executors.newSingleThreadScheduledExecutor()


        val runnable = Runnable {
            kotlin.run {
                // Get the current user id
                val currentUserID = Firestore().getCurrentUserID()

                if (currentUserID.isNotEmpty()) {
                    // Start the Main Activity
                    startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
                } else {
                    // Start the Intro Activity
                    startActivity(Intent(this@SplashScreenActivity, IntroScreen::class.java))
                }
            }
        }

        scheduledExecutorService.schedule(runnable, 2000, TimeUnit.MILLISECONDS)









        setContentView(mBinding.root)
    }


}