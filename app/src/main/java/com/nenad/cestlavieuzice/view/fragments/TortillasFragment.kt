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
import com.nenad.cestlavieuzice.databinding.FragmentTortillasBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TortillasFragment : Fragment() {

    private lateinit var mBinding: FragmentTortillasBinding
    lateinit var dish: Dish
    lateinit var path: Uri
    lateinit var action: TortillasFragmentDirections.ActionTortillasFragmentToOverviewFragment


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_tortillas, container, false)





        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListeners()
    }

    fun setOnClickListeners() {
        mBinding.ham.setOnClickListener {
            path =
                Uri.parse("android.resource://com.nenad.cestlavieuzice/" + R.drawable.tortilja_sunka_1)
            val imgPath: String = path.toString()
            dish = Dish(
                null, "Tortilja sa sunkom",
                null, "Šunka, trapist, pavlaka + dodaci po izboru", true, true, 1, 280, 360,
                imgPath
            )
            action =
                TortillasFragmentDirections.actionTortillasFragmentToOverviewFragment(dish) as TortillasFragmentDirections.ActionTortillasFragmentToOverviewFragment

            findNavController().navigate(action)
        }
        mBinding.chickenBreasts.setOnClickListener {
            path =
                Uri.parse("android.resource://com.nenad.cestlavieuzice/" + R.drawable.tortilja_pileca_prsa)
            val imgPath: String = path.toString()
            dish = Dish(
                null, "Tortilja sa pilecim prsima",
                null, "Pileće grudi, trapist, pavlaka + dodaci po izboru", true, true, 1, 280, 360,
                imgPath
            )
            action =
                TortillasFragmentDirections.actionTortillasFragmentToOverviewFragment(dish) as TortillasFragmentDirections.ActionTortillasFragmentToOverviewFragment

            findNavController().navigate(action)
        }
        mBinding.kulen.setOnClickListener {
            path =
                Uri.parse("android.resource://com.nenad.cestlavieuzice/" + R.drawable.tortilja_kulen)
            val imgPath: String = path.toString()
            dish = Dish(
                null, "Tortilja sa kulenom",
                null, "Kulen, trapist, pavlaka + dodaci po izboru", true, true, 1, 250, 320,
                imgPath
            )
            action =
                TortillasFragmentDirections.actionTortillasFragmentToOverviewFragment(dish) as TortillasFragmentDirections.ActionTortillasFragmentToOverviewFragment

            findNavController().navigate(action)
        }
        mBinding.suviVrat.setOnClickListener {
            path =
                Uri.parse("android.resource://com.nenad.cestlavieuzice/" + R.drawable.tortilja_suvi_vrat)
            val imgPath: String = path.toString()
            dish = Dish(
                null, "Tortilja sa suvim vratom",
                null, "Suvi vrat, trapist, pavlaka + dodaci po izboru", true, true, 1, 300, 370,
                imgPath
            )
            action =
                TortillasFragmentDirections.actionTortillasFragmentToOverviewFragment(dish) as TortillasFragmentDirections.ActionTortillasFragmentToOverviewFragment

            findNavController().navigate(action)
        }
        mBinding.cajna.setOnClickListener {
            path =
                Uri.parse("android.resource://com.nenad.cestlavieuzice/" + R.drawable.tortilja_cajna)
            val imgPath: String = path.toString()
            dish = Dish(
                null, "Tortilja sa cajnom",
                null, "Čajna, trapist, pavlaka + dodaci po izboru", true, true, 1, 230, 310,
                imgPath
            )
            action =
                TortillasFragmentDirections.actionTortillasFragmentToOverviewFragment(dish) as TortillasFragmentDirections.ActionTortillasFragmentToOverviewFragment

            findNavController().navigate(action)
        }
        mBinding.pecenica.setOnClickListener {
            path =
                Uri.parse("android.resource://com.nenad.cestlavieuzice/" + R.drawable.tortilja_pecenica)
            val imgPath: String = path.toString()
            dish = Dish(
                null, "Tortilja sa pecenicom",
                null, "Pečenica, trapist, pavlaka + dodaci po izboru", true, true, 1, 290, 370,
                imgPath
            )
            action =
                TortillasFragmentDirections.actionTortillasFragmentToOverviewFragment(dish) as TortillasFragmentDirections.ActionTortillasFragmentToOverviewFragment

            findNavController().navigate(action)
        }
        mBinding.turkeyBreasts.setOnClickListener {
            path =
                Uri.parse("android.resource://com.nenad.cestlavieuzice/" + R.drawable.tortilja_cureca_prsa)
            val imgPath: String = path.toString()
            dish = Dish(
                null, "Tortilja sa curecim prsima",
                null, "Ćureće grudi, trapist, pavlaka + dodaci po izboru", true, true, 1, 280, 340,
                imgPath
            )
            action =
                TortillasFragmentDirections.actionTortillasFragmentToOverviewFragment(dish) as TortillasFragmentDirections.ActionTortillasFragmentToOverviewFragment

            findNavController().navigate(action)
        }
        mBinding.prsuta.setOnClickListener {
            path =
                Uri.parse("android.resource://com.nenad.cestlavieuzice/" + R.drawable.tortilja_prsuta)
            val imgPath: String = path.toString()
            dish = Dish(
                null, "Tortilja sa prsutom",
                null, "Pršuta, trapist, pavlaka + dodaci po izboru", true, true, 1, 260, 310,
                imgPath
            )
            action =
                TortillasFragmentDirections.actionTortillasFragmentToOverviewFragment(dish) as TortillasFragmentDirections.ActionTortillasFragmentToOverviewFragment

            findNavController().navigate(action)
        }


    }


}