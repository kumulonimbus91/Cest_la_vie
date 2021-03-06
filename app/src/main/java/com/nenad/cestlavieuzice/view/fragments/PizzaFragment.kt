package com.nenad.cestlavieuzice.view.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.nenad.cestlavieuzice.R
import com.nenad.cestlavieuzice.database.model.Dish
import com.nenad.cestlavieuzice.databinding.FragmentPizzaBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PizzaFragment : Fragment() {

    private lateinit var mBinding: FragmentPizzaBinding
    lateinit var dish: Dish
    lateinit var path: Uri
    lateinit var action: PizzaFragmentDirections.ActionPizzaFragmentToOverviewFragment
    lateinit var mNavController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_pizza, container, false)

        requireActivity().findViewById<ViewGroup>(R.id.ll_go).visibility = View.VISIBLE



        return mBinding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListeners()
        mNavController = findNavController()
    }

    fun setOnClickListeners() {
        mBinding.pizzaCapri.setOnClickListener {
            path =
                Uri.parse("android.resource://com.nenad.cestlavieuzice/" + R.drawable.pizza_capricciosa)
            val imgPath: String = path.toString()
            dish = Dish(
                null, "Pizza Capricciosa",
                null, "Kečap, sir, šunka, pečurke - 30cm ", false, false, 1, 340,
                null, imgPath
            )
            action =
                PizzaFragmentDirections.actionPizzaFragmentToOverviewFragment(dish) as PizzaFragmentDirections.ActionPizzaFragmentToOverviewFragment
            mNavController.navigate(action)


        }
        mBinding.pizzaSiciliana.setOnClickListener {
            path =
                Uri.parse("android.resource://com.nenad.cestlavieuzice/" + R.drawable.pizza_siciliana)
            val imgPath: String = path.toString()
            dish = Dish(
                null,
                "Pizza Siciliana",
                null,
                "Kecap,sir, pečurke, feferoni, suvi vrat, pršuta, feta sir - 30cm",
                false,
                false,
                1,
                340,
                null,
                imgPath
            )
            action =
                PizzaFragmentDirections.actionPizzaFragmentToOverviewFragment(dish) as PizzaFragmentDirections.ActionPizzaFragmentToOverviewFragment
            mNavController.navigate(action)
        }
        mBinding.pizzaCest.setOnClickListener {
            path =
                Uri.parse("android.resource://com.nenad.cestlavieuzice/" + R.drawable.pizza_cest_la_vie)
            val imgPath: String = path.toString()
            dish = Dish(
                null,
                "Pizza Cest La Vie",
                null,
                "Kečap, sir, šunka, kobasica, suvi vrat, pečurke, feferoni, feta sir - 30cm",
                false,
                false,
                1,
                340,
                null,
                imgPath
            )
            action =
                PizzaFragmentDirections.actionPizzaFragmentToOverviewFragment(dish) as PizzaFragmentDirections.ActionPizzaFragmentToOverviewFragment
            mNavController.navigate(action)
        }
        mBinding.pizzaQuatro.setOnClickListener {
            path =
                Uri.parse("android.resource://com.nenad.cestlavieuzice/" + R.drawable.pizza_quatro_stagione)
            val imgPath: String = path.toString()
            dish = Dish(
                null,
                "Pizza Quatro Stagione",
                null,
                "Kečap, sir, šunka, pečurke,suvi vrat, slanina, jaja, pavlaka - 30cm",
                false,
                false,
                1,
                340,
                null,
                imgPath
            )
            action =
                PizzaFragmentDirections.actionPizzaFragmentToOverviewFragment(dish) as PizzaFragmentDirections.ActionPizzaFragmentToOverviewFragment
            mNavController.navigate(action)

        }
        mBinding.pizzaVojvodj.setOnClickListener {
            path =
                Uri.parse("android.resource://com.nenad.cestlavieuzice/" + R.drawable.pizza_vojvodjanka)
            val imgPath: String = path.toString()
            dish = Dish(
                null, "Pizza Vojvodjanka",
                null, "Kečap, sir, pečurke, slanina, jaja, feferoni - 30cm", false, false, 1, 340,
                null, imgPath
            )
            action =
                PizzaFragmentDirections.actionPizzaFragmentToOverviewFragment(dish) as PizzaFragmentDirections.ActionPizzaFragmentToOverviewFragment
            mNavController.navigate(action)
        }
        mBinding.pizzaVege.setOnClickListener {
            path =
                Uri.parse("android.resource://com.nenad.cestlavieuzice/" + R.drawable.pizza_vegeterijana)
            val imgPath: String = path.toString()
            dish = Dish(
                null, "Pizza Vegeterijana",
                null, "Kečap, sir, pečurke, paprika, masline - 30cm", false, false, 1, 340,
                null, imgPath
            )
            action =
                PizzaFragmentDirections.actionPizzaFragmentToOverviewFragment(dish) as PizzaFragmentDirections.ActionPizzaFragmentToOverviewFragment
            mNavController.navigate(action)
        }

        mBinding.pizzaPorodicnaCapr.setOnClickListener {
            path =
                Uri.parse("android.resource://com.nenad.cestlavieuzice/" + R.drawable.pizza_capricciosa)
            val imgPath: String = path.toString()
            dish = Dish(
                null, "Porodična Capricciosa",
                null, "Kečap, sir, šunka, pečurke - 42cm", false, false, 1, 1050,
                null, imgPath
            )
            action =
                PizzaFragmentDirections.actionPizzaFragmentToOverviewFragment(dish) as PizzaFragmentDirections.ActionPizzaFragmentToOverviewFragment
            mNavController.navigate(action)
        }
        mBinding.pizzaPorodicnaQuatr.setOnClickListener {
            path =
                Uri.parse("android.resource://com.nenad.cestlavieuzice/" + R.drawable.pizza_quatro_stagione)
            val imgPath: String = path.toString()
            dish = Dish(
                null,
                "Porodična Quatro Stagione",
                null,
                "Kečap, sir, šunka, pečurke,suvi vrat, slanina, jaja, pavlaka - 42cm",
                false,
                false,
                1,
                1090,
                null,
                imgPath
            )
            action =
                PizzaFragmentDirections.actionPizzaFragmentToOverviewFragment(dish) as PizzaFragmentDirections.ActionPizzaFragmentToOverviewFragment
            mNavController.navigate(action)


        }
    }

}

