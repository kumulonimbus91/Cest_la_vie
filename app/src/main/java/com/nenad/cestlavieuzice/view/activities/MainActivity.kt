package com.nenad.cestlavieuzice.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.*
import androidx.navigation.NavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.nenad.cestlavieuzice.R
import com.nenad.cestlavieuzice.databinding.ActivityMainBinding
import com.nenad.cestlavieuzice.view.fragments.*


class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        mNavController = navHostFragment.findNavController()

        setOnClickListeners()

        supportActionBar?.hide()





        setContentView(mBinding.root)
    }
   fun setOnClickListeners() {
       mBinding.pizzeBtn.setOnClickListener {
           supportFragmentManager.commit {
               replace(R.id.nav_host_fragment, PizzaFragment())
           }
       }
       mBinding.burgeri.setOnClickListener {
           supportFragmentManager.commit {
               replace(R.id.nav_host_fragment, BurgersFragment())
           }
       }
       mBinding.tortilje.setOnClickListener {
           supportFragmentManager.commit {
               replace(R.id.nav_host_fragment, TortillasFragment())
           }
       }
       mBinding.sendvici.setOnClickListener {
           supportFragmentManager.commit {
               replace(R.id.nav_host_fragment, SandwichesFragment())
           }
       }
       mBinding.deserts.setOnClickListener {
           supportFragmentManager.commit {
               replace(R.id.nav_host_fragment, DesertsFragment())
           }
       }
       mBinding.ostalo.setOnClickListener {
           supportFragmentManager.commit {
               replace(R.id.nav_host_fragment, OtherFragment())
           }
       }
   }
}