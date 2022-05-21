package com.nenad.cestlavieuzice.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
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
    }
}