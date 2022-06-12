package com.nenad.cestlavieuzice.view.activities

import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.auth.FirebaseAuth
import com.nenad.cestlavieuzice.R
import com.nenad.cestlavieuzice.databinding.ActivityMainBinding
import com.nenad.cestlavieuzice.view.fragments.*
import com.nenad.cestlavieuzice.viewmodel.ViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mNavController: NavController
    lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        mNavController = navHostFragment.navController




        setOnClickListeners()
        drawerItemSelectedListener()
        mBinding.pizzeBtn.background =  ContextCompat.getDrawable(this, R.drawable.main_menu_btn_selected)

        viewModel = ViewModelProvider(this)[ViewModel::class.java]

        supportActionBar?.hide()

    }

    override fun onSupportNavigateUp(): Boolean {
        mNavController = this.findNavController(R.id.nav_host_fragment)
        return mNavController.navigateUp()
    }

    fun setOnClickListeners() {
        mBinding.pizzeBtn.setOnClickListener {

            mNavController.navigate(R.id.pizzaFragment)
            mBinding.pizzeBtn.background =  ContextCompat.getDrawable(this, R.drawable.main_menu_btn_selected)
            mBinding.tortilje.background = ContextCompat.getDrawable(this, R.drawable.btn_selected)
            mBinding.sendvici.background = ContextCompat.getDrawable(this, R.drawable.btn_selected)
            mBinding.deserts.background = ContextCompat.getDrawable(this, R.drawable.btn_selected)
            mBinding.burgeri.background = ContextCompat.getDrawable(this, R.drawable.btn_selected)
            mBinding.ostalo.background = ContextCompat.getDrawable(this, R.drawable.btn_selected)
        }
        mBinding.burgeri.setOnClickListener {

            mNavController.navigate(R.id.burgersFragment)
            mBinding.pizzeBtn.background =  ContextCompat.getDrawable(this, R.drawable.btn_selected)
            mBinding.tortilje.background = ContextCompat.getDrawable(this, R.drawable.btn_selected)
            mBinding.sendvici.background = ContextCompat.getDrawable(this, R.drawable.btn_selected)
            mBinding.deserts.background = ContextCompat.getDrawable(this, R.drawable.btn_selected)
            mBinding.burgeri.background =  ContextCompat.getDrawable(this, R.drawable.main_menu_btn_selected)
            mBinding.ostalo.background = ContextCompat.getDrawable(this, R.drawable.btn_selected)
        }
        mBinding.tortilje.setOnClickListener {

            mNavController.navigate(R.id.tortillasFragment)
            mBinding.pizzeBtn.background =  ContextCompat.getDrawable(this, R.drawable.btn_selected)
            mBinding.tortilje.background = ContextCompat.getDrawable(this, R.drawable.main_menu_btn_selected)
            mBinding.sendvici.background = ContextCompat.getDrawable(this, R.drawable.btn_selected)
            mBinding.deserts.background = ContextCompat.getDrawable(this, R.drawable.btn_selected)
            mBinding.burgeri.background =  ContextCompat.getDrawable(this, R.drawable.btn_selected)
            mBinding.ostalo.background = ContextCompat.getDrawable(this, R.drawable.btn_selected)
        }
        mBinding.sendvici.setOnClickListener {

            mNavController.navigate(R.id.sandwichesFragment)
            mBinding.pizzeBtn.background =  ContextCompat.getDrawable(this, R.drawable.btn_selected)
            mBinding.tortilje.background = ContextCompat.getDrawable(this, R.drawable.btn_selected)
            mBinding.sendvici.background = ContextCompat.getDrawable(this, R.drawable.main_menu_btn_selected)
            mBinding.deserts.background = ContextCompat.getDrawable(this, R.drawable.btn_selected)
            mBinding.burgeri.background =  ContextCompat.getDrawable(this, R.drawable.btn_selected)
            mBinding.ostalo.background = ContextCompat.getDrawable(this, R.drawable.btn_selected)
        }
        mBinding.deserts.setOnClickListener {

            mNavController.navigate(R.id.desertsFragment)
            mBinding.pizzeBtn.background =  ContextCompat.getDrawable(this, R.drawable.btn_selected)
            mBinding.tortilje.background = ContextCompat.getDrawable(this, R.drawable.btn_selected)
            mBinding.sendvici.background = ContextCompat.getDrawable(this, R.drawable.btn_selected)
            mBinding.deserts.background = ContextCompat.getDrawable(this, R.drawable.main_menu_btn_selected)
            mBinding.burgeri.background =  ContextCompat.getDrawable(this, R.drawable.btn_selected)
            mBinding.ostalo.background = ContextCompat.getDrawable(this, R.drawable.btn_selected)
        }
        mBinding.ostalo.setOnClickListener {

            mNavController.navigate(R.id.otherFragment)
            mBinding.pizzeBtn.background =  ContextCompat.getDrawable(this, R.drawable.btn_selected)
            mBinding.tortilje.background = ContextCompat.getDrawable(this, R.drawable.btn_selected)
            mBinding.sendvici.background = ContextCompat.getDrawable(this, R.drawable.btn_selected)
            mBinding.deserts.background = ContextCompat.getDrawable(this, R.drawable.btn_selected)
            mBinding.burgeri.background =  ContextCompat.getDrawable(this, R.drawable.btn_selected)
            mBinding.ostalo.background = ContextCompat.getDrawable(this, R.drawable.main_menu_btn_selected)
        }
        mBinding.imgMenu.setOnClickListener {

            if (mBinding.drawerLayout.isOpen) {
                mBinding.drawerLayout.closeDrawer(Gravity.LEFT)
            } else {
                mBinding.drawerLayout.openDrawer(Gravity.LEFT)
            }


        }
    }

    private fun drawerItemSelectedListener() {
        mBinding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {

                R.id.nav_sign_out -> {

                    FirebaseAuth.getInstance().signOut()


                    val intent = Intent(this@MainActivity, IntroScreen::class.java)
                    startActivity(intent)
                    finish()





                    return@setNavigationItemSelectedListener true

                }
                R.id.history -> {

                    mBinding.drawerLayout.closeDrawer(Gravity.LEFT)
                    mNavController.navigate(R.id.historyFragment)


                    return@setNavigationItemSelectedListener true
                }
                else -> {
                    return@setNavigationItemSelectedListener false
                }


            }


        }


    }

}