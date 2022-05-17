package com.nenad.cestlavieuzice.view.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.nenad.cestlavieuzice.R
import com.nenad.cestlavieuzice.database.model.Dish
import com.nenad.cestlavieuzice.databinding.FragmentPizzaBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PizzaFragment : Fragment() {

    private lateinit var mBinding: FragmentPizzaBinding
    lateinit var dish:Dish
    lateinit var path: Uri
    lateinit var action: PizzaFragmentDirections.ActionPizzaFragmentToOverviewFragment


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_pizza, container, false)

        setOnClickListeners()

        return mBinding.root

    }

    fun setOnClickListeners() {
        mBinding.pizzaCapri.setOnClickListener {
            path =
                Uri.parse("android.resource://com.nenad.cestlavieuzice/" + R.drawable.pizza_capricciosa)
            val imgPath: String = path.toString()
            dish = Dish(
                null, "Pizza Capricciosa",
                null, "Kecap, Pavlaka, Majonez, Sunka", false, false, 1, 340,
                null,imgPath
            )
            action = PizzaFragmentDirections.actionPizzaFragmentToOverviewFragment(dish)

            findNavController().navigate(action)
        }
        mBinding.pizzaSiciliana.setOnClickListener {
            path =
                Uri.parse("android.resource://com.nenad.cestlavieuzice/" + R.drawable.pizza_siciliana)
            val imgPath: String = path.toString()
            dish = Dish(
                null, "Pizza Siciliana",
                null, "Kecap, Pavlaka, Majonez, Sunka", false, false, 1, 340,
                null,imgPath
            )
            action = PizzaFragmentDirections.actionPizzaFragmentToOverviewFragment(dish)

            findNavController().navigate(action)
        }
        mBinding.pizzaCest.setOnClickListener {
            path =
                Uri.parse("android.resource://com.nenad.cestlavieuzice/" + R.drawable.pizza_cest_la_vie)
            val imgPath: String = path.toString()
            dish = Dish(
                null, "Pizza Cest La Vie",
                null, "Kecap, Pavlaka, Majonez, Sunka", false, false, 1, 340,
                null,imgPath
            )
            action = PizzaFragmentDirections.actionPizzaFragmentToOverviewFragment(dish)

            findNavController().navigate(action)
        }
        mBinding.pizzaQuatro.setOnClickListener {
            path =
                Uri.parse("android.resource://com.nenad.cestlavieuzice/" + R.drawable.pizza_quatro_stagione)
            val imgPath: String = path.toString()
            dish = Dish(
                null, "Pizza Quatro Stagione",
                null, "Kecap, Pavlaka, Majonez, Sunka", false, false, 1, 340,
                null,imgPath
            )
            action = PizzaFragmentDirections.actionPizzaFragmentToOverviewFragment(dish)

            findNavController().navigate(action)
        }
        mBinding.pizzaVojvodj.setOnClickListener {
            path =
                Uri.parse("android.resource://com.nenad.cestlavieuzice/" + R.drawable.pizza_vojvodjanka)
            val imgPath: String = path.toString()
            dish = Dish(
                null, "Pizza Vojvodjanka",
                null, "Kecap, Pavlaka, Majonez, Sunka", false, false, 1, 340,
                null,imgPath
            )
            action = PizzaFragmentDirections.actionPizzaFragmentToOverviewFragment(dish)

            findNavController().navigate(action)
        }
        mBinding.pizzaVege.setOnClickListener {
            path =
                Uri.parse("android.resource://com.nenad.cestlavieuzice/" + R.drawable.pizza_vegeterijana)
            val imgPath: String = path.toString()
            dish = Dish(
                null, "Pizza Vegeterijana",
                null, "Kecap, Pavlaka, Majonez, Sunka", false, false, 1, 340,
                null,imgPath
            )
            action = PizzaFragmentDirections.actionPizzaFragmentToOverviewFragment(dish)

            findNavController().navigate(action)
        }

        mBinding.pizzaPorodicnaCapr.setOnClickListener {
            path =
                Uri.parse("android.resource://com.nenad.cestlavieuzice/" + R.drawable.pizza_capricciosa)
            val imgPath: String = path.toString()
            dish = Dish(
                null, "Porodicna Capricciosa",
                null, "Kecap, Pavlaka, Majonez, Sunka", false, false, 1, 910,
                null,imgPath
            )
            action = PizzaFragmentDirections.actionPizzaFragmentToOverviewFragment(dish)

            findNavController().navigate(action)
        }
        mBinding.pizzaPorodicnaQuatr.setOnClickListener {
            path =
                Uri.parse("android.resource://com.nenad.cestlavieuzice/" + R.drawable.pizza_quatro_stagione)
            val imgPath: String = path.toString()
            dish = Dish(
                null, "Porodicna Quatro Stagione",
                null, "Kecap, Pavlaka, Majonez, Sunka", false, false, 1, 940,
                null,imgPath
            )
            action = PizzaFragmentDirections.actionPizzaFragmentToOverviewFragment(dish)

            findNavController().navigate(action)
        }
    }

}

//val action = HomeFragmentDirections.actionHomeFragmentToOverviewFragment(it)
//findNavController().navigate(action)