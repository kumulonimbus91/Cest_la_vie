package com.nenad.cestlavieuzice.view.fragments

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.nenad.cestlavieuzice.R
import com.nenad.cestlavieuzice.database.model.Dish
import com.nenad.cestlavieuzice.databinding.FragmentBurgersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BurgersFragment : Fragment() {

    private lateinit var mBinding: FragmentBurgersBinding
    lateinit var dish: Dish
    lateinit var path: Uri
    lateinit var action: BurgersFragmentDirections.ActionBurgersFragmentToOverviewFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_burgers, container, false)
        setOnClickListeners()



        return mBinding.root
    }
    fun setOnClickListeners() {
        mBinding.burgerKlasik.setOnClickListener {
            path =
                Uri.parse("android.resource://com.nenad.cestlavieuzice/" + R.drawable.burger_klasik)
            val imgPath: String = path.toString()
            dish = Dish(
                null, "Burger Klasik",
                null, "Govedina, burger preliv, Hoffmaster sir, Heinz kecap, Iceberg salata", false, false, 1, 540,
                null, imgPath
            )
            action = BurgersFragmentDirections.actionBurgersFragmentToOverviewFragment(dish)

            findNavController().navigate(action)
        }
        mBinding.klasikXxl.setOnClickListener {
            path =
                Uri.parse("android.resource://com.nenad.cestlavieuzice/" + R.drawable.burger_xxl)
            val imgPath: String = path.toString()
            dish = Dish(
                null, "Burger Klasik XXL",
                null, "Govedina, burger preliv, Hoffmaster sir, Heinz kecap, Iceberg salata", false, false, 1, 640,
                null, imgPath
            )
            action = BurgersFragmentDirections.actionBurgersFragmentToOverviewFragment(dish)

            findNavController().navigate(action)
        }
        mBinding.burgerGurman.setOnClickListener {
            path =
                Uri.parse("android.resource://com.nenad.cestlavieuzice/" + R.drawable.burger_gurman)
            val imgPath: String = path.toString()
            dish = Dish(
                null, "Burger Gurman",
                null, "Govedina, burger preliv, Hoffmaster sir, Heinz kecap, Iceberg salata", false, false, 1, 540,
                null, imgPath
            )
            action = BurgersFragmentDirections.actionBurgersFragmentToOverviewFragment(dish)

            findNavController().navigate(action)
        }
        mBinding.fishburger.setOnClickListener {
            path =
                Uri.parse("android.resource://com.nenad.cestlavieuzice/" + R.drawable.burger_fishburger)
            val imgPath: String = path.toString()
            dish = Dish(
                null, "Fishburger",
                null, "Govedina, burger preliv, Hoffmaster sir, Heinz kecap, Iceberg salata", false, false, 1, 540,
                null, imgPath
            )
            action = BurgersFragmentDirections.actionBurgersFragmentToOverviewFragment(dish)

            findNavController().navigate(action)
        }
    }


}