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
import com.nenad.cestlavieuzice.databinding.FragmentSandwichesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SandwichesFragment : Fragment() {

    private lateinit var mBinding: FragmentSandwichesBinding
    lateinit var dish: Dish
    lateinit var path: Uri
    lateinit var action: SandwichesFragmentDirections.ActionSandwichesFragmentToOverviewFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sandwiches, container, false)

        setOnClickListeners()

        return mBinding.root
    }

    fun setOnClickListeners() {
        mBinding.ham.setOnClickListener {
            path =
                Uri.parse("android.resource://com.nenad.cestlavieuzice/" + R.drawable.sendvic_sa_sunkom)
            val imgPath: String = path.toString()
            dish = Dish(
                null, "Sendvic sa sunkom",
                null, "Kecap, Pavlaka, Majonez, Sunka", true, true, 1, 240,330,
                imgPath
            )
            action = SandwichesFragmentDirections.actionSandwichesFragmentToOverviewFragment(dish)

            findNavController().navigate(action)
        }
        mBinding.chickenBreasts.setOnClickListener {
            path =
                Uri.parse("android.resource://com.nenad.cestlavieuzice/" + R.drawable.sendvic_pileca_prsa)
            val imgPath: String = path.toString()
            dish = Dish(
                null, "Sendvic pileca prsa",
                null, "Kecap, Pavlaka, Majonez, Sunka", true, true, 1, 260,350,
                imgPath
            )
            action = SandwichesFragmentDirections.actionSandwichesFragmentToOverviewFragment(dish)

            findNavController().navigate(action)
        }
        mBinding.kulen.setOnClickListener {
            path =
                Uri.parse("android.resource://com.nenad.cestlavieuzice/" + R.drawable.sendvic_sa_kulenom)
            val imgPath: String = path.toString()
            dish = Dish(
                null, "Sendvic sa kulenom",
                null, "Kecap, Pavlaka, Majonez, Sunka", true, true, 1, 220,310,
                imgPath
            )
            action = SandwichesFragmentDirections.actionSandwichesFragmentToOverviewFragment(dish)

            findNavController().navigate(action)
        }
        mBinding.cajna.setOnClickListener {
            path =
                Uri.parse("android.resource://com.nenad.cestlavieuzice/" + R.drawable.sendvic_sa_cajnom)
            val imgPath: String = path.toString()
            dish = Dish(
                null, "Sendvic sa cajnom",
                null, "Kecap, Pavlaka, Majonez, Sunka", true, true, 1, 200,280,
                imgPath
            )
            action = SandwichesFragmentDirections.actionSandwichesFragmentToOverviewFragment(dish)

            findNavController().navigate(action)
        }
        mBinding.pecenica.setOnClickListener {
            path =
                Uri.parse("android.resource://com.nenad.cestlavieuzice/" + R.drawable.sendvic_sa_pecenicom)
            val imgPath: String = path.toString()
            dish = Dish(
                null, "Sendvic sa pecenicom",
                null, "Kecap, Pavlaka, Majonez, Sunka", true, true, 1, 250,330,
                imgPath
            )
            action = SandwichesFragmentDirections.actionSandwichesFragmentToOverviewFragment(dish)

            findNavController().navigate(action)
        }
        mBinding.prsuta.setOnClickListener {
            path =
                Uri.parse("android.resource://com.nenad.cestlavieuzice/" + R.drawable.sendvic_sa_prsutom)
            val imgPath: String = path.toString()
            dish = Dish(
                null, "Sendvic sa prsutom",
                null, "Kecap, Pavlaka, Majonez, Sunka", true, true, 1, 270,360,
                imgPath
            )
            action = SandwichesFragmentDirections.actionSandwichesFragmentToOverviewFragment(dish)

            findNavController().navigate(action)
        }
        mBinding.turkeyBreasts.setOnClickListener {
            path =
                Uri.parse("android.resource://com.nenad.cestlavieuzice/" + R.drawable.sendvic_sa_curecim_prsima)
            val imgPath: String = path.toString()
            dish = Dish(
                null, "Sendvic sa curecim prsima",
                null, "Kecap, Pavlaka, Majonez, Sunka", true, true, 1, 270,360,
                imgPath
            )
            action = SandwichesFragmentDirections.actionSandwichesFragmentToOverviewFragment(dish)

            findNavController().navigate(action)
        }





    }

}