package com.nenad.cestlavieuzice.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
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
        }
        mBinding.burgeri.setOnClickListener {

            mNavController.navigate(R.id.burgersFragment)
        }
        mBinding.tortilje.setOnClickListener {

            mNavController.navigate(R.id.tortillasFragment)
        }
        mBinding.sendvici.setOnClickListener {

            mNavController.navigate(R.id.sandwichesFragment)
        }
        mBinding.deserts.setOnClickListener {

            mNavController.navigate(R.id.desertsFragment)
        }
        mBinding.ostalo.setOnClickListener {

            mNavController.navigate(R.id.otherFragment)
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

        var newFragment: Fragment
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()

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
//                  newFragment = HistoryFragment()
//                    transaction.replace(mBinding.navHostFragment.id, newFragment)
//                    transaction.commit()

                    mNavController.navigate(R.id.historyFragment)



//                    val intent = Intent(this@MainActivity, IntroScreen::class.java)
//                    startActivity(intent)
//                    finish()





                    return@setNavigationItemSelectedListener true
                }
                R.id.settings -> {
                    val intent = Intent(this@MainActivity, IntroScreen::class.java)
                    startActivity(intent)
                    finish()







                    return@setNavigationItemSelectedListener true
                }


                else -> {
                    return@setNavigationItemSelectedListener false
                }


            }


        }


    }
}