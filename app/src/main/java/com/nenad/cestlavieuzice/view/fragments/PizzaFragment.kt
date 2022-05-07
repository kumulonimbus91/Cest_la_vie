package com.nenad.cestlavieuzice.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.nenad.cestlavieuzice.R
import com.nenad.cestlavieuzice.databinding.FragmentPizzaBinding

class PizzaFragment : Fragment() {

    private lateinit var mBinding: FragmentPizzaBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_pizza, container, false)

        return mBinding.root

    }

    fun setOnClickListeners() {
        mBinding.pizzaCapri.setOnClickListener {

        }
    }

}