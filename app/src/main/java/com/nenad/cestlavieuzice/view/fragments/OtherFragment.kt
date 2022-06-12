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
import com.nenad.cestlavieuzice.databinding.FragmentOtherBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OtherFragment : Fragment() {

    private lateinit var mBinding: FragmentOtherBinding
    lateinit var dish: Dish
    lateinit var path: Uri
    lateinit var action: OtherFragmentDirections.ActionOtherFragmentToOverviewFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_other, container, false)

        setOnClickListeners()

        return mBinding.root
    }

    fun setOnClickListeners() {
        mBinding.kompletLepinja.setOnClickListener {
            path =
                Uri.parse("android.resource://com.nenad.cestlavieuzice/" + R.drawable.komplet_lepinja)
            val imgPath: String = path.toString()
            dish = Dish(
                null, "Komplet lepinja",
                null, "Pretop, Kajmak, Jaja", false, false, 1, 280,360,
                imgPath
            )
            action = OtherFragmentDirections.actionOtherFragmentToOverviewFragment(dish)

            findNavController().navigate(action)
        }

        mBinding.frenchFries.setOnClickListener {
            path =
                Uri.parse("android.resource://com.nenad.cestlavieuzice/" + R.drawable.pomfrit)
            val imgPath: String = path.toString()
            dish = Dish(
                null, "Pomfrit",
                null, null, true, false, 1, 110,160,
                imgPath
            )
            action = OtherFragmentDirections.actionOtherFragmentToOverviewFragment(dish)

            findNavController().navigate(action)
        }
        mBinding.girosPileci.setOnClickListener {
            path =
                Uri.parse("android.resource://com.nenad.cestlavieuzice/" + R.drawable.giros_pileci)
            val imgPath: String = path.toString()
            dish = Dish(
                null, "Pileci giros",
                null, null, true, true, 1, 280,360,
                imgPath
            )
            action = OtherFragmentDirections.actionOtherFragmentToOverviewFragment(dish)

            findNavController().navigate(action)
        }
        mBinding.girosSvinjski.setOnClickListener {
            path =
                Uri.parse("android.resource://com.nenad.cestlavieuzice/" + R.drawable.giros_pileci)
            val imgPath: String = path.toString()
            dish = Dish(
                null, "Svinjski giros",
                null, null, true, true, 1, 280,360,
                imgPath
            )
            action = OtherFragmentDirections.actionOtherFragmentToOverviewFragment(dish)

            findNavController().navigate(action)
        }
        mBinding.krainer.setOnClickListener {
            path =
                Uri.parse("android.resource://com.nenad.cestlavieuzice/" + R.drawable.krainer_kobasica)
            val imgPath: String = path.toString()
            dish = Dish(
                null, "Pomfrit",
                null, null, false, false, 1, 330,330,
                imgPath
            )
            action = OtherFragmentDirections.actionOtherFragmentToOverviewFragment(dish)

            findNavController().navigate(action)
        }
    }


}