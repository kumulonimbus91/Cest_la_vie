package com.nenad.cestlavieuzice.view.fragments

import android.net.Uri
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.findNavController
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
    lateinit var mNavController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sandwiches, container, false)



        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListeners()
        mNavController = findNavController()
    }

    fun setOnClickListeners() {
        mBinding.ham.setOnClickListener {
            path =
                Uri.parse("android.resource://com.nenad.cestlavieuzice/" + R.drawable.sendvic_sa_sunkom)
            val imgPath: String = path.toString()
            dish = Dish(
                null, "Sendvic sa sunkom",
                null, "Šunka + dodaci po izboru", true, true, 1, 240, 330,
                imgPath
            )
            action =
                SandwichesFragmentDirections.actionSandwichesFragmentToOverviewFragment(dish) as SandwichesFragmentDirections.ActionSandwichesFragmentToOverviewFragment
            mNavController.navigate(action)

        }
        mBinding.chickenBreasts.setOnClickListener {
            path =
                Uri.parse("android.resource://com.nenad.cestlavieuzice/" + R.drawable.sendvic_pileca_prsa)
            val imgPath: String = path.toString()
            dish = Dish(
                null, "Sendvic pileca prsa",
                null, "Pileća prsa + dodaci po izboru", true, true, 1, 260, 350,
                imgPath
            )
            action =
                SandwichesFragmentDirections.actionSandwichesFragmentToOverviewFragment(dish) as SandwichesFragmentDirections.ActionSandwichesFragmentToOverviewFragment
            mNavController.navigate(action)
        }
        mBinding.kulen.setOnClickListener {
            path =
                Uri.parse("android.resource://com.nenad.cestlavieuzice/" + R.drawable.sendvic_sa_kulenom)
            val imgPath: String = path.toString()
            dish = Dish(
                null, "Sendvic sa kulenom",
                null, "Kulen + dodaci po izboru", true, true, 1, 220, 310,
                imgPath
            )
            action =
                SandwichesFragmentDirections.actionSandwichesFragmentToOverviewFragment(dish) as SandwichesFragmentDirections.ActionSandwichesFragmentToOverviewFragment
            mNavController.navigate(action)
        }
        mBinding.cajna.setOnClickListener {
            path =
                Uri.parse("android.resource://com.nenad.cestlavieuzice/" + R.drawable.sendvic_sa_cajnom)
            val imgPath: String = path.toString()
            dish = Dish(
                null, "Sendvic sa cajnom",
                null, "Čajna + dodaci po izboru", true, true, 1, 200, 280,
                imgPath
            )
            action =
                SandwichesFragmentDirections.actionSandwichesFragmentToOverviewFragment(dish) as SandwichesFragmentDirections.ActionSandwichesFragmentToOverviewFragment
            mNavController.navigate(action)
        }
        mBinding.pecenica.setOnClickListener {
            path =
                Uri.parse("android.resource://com.nenad.cestlavieuzice/" + R.drawable.sendvic_sa_pecenicom)
            val imgPath: String = path.toString()
            dish = Dish(
                null, "Sendvic sa pecenicom",
                null, "Pečenica + dodaci po izboru", true, true, 1, 250, 330,
                imgPath
            )
            action =
                SandwichesFragmentDirections.actionSandwichesFragmentToOverviewFragment(dish) as SandwichesFragmentDirections.ActionSandwichesFragmentToOverviewFragment
            mNavController.navigate(action)
        }
        mBinding.prsuta.setOnClickListener {
            path =
                Uri.parse("android.resource://com.nenad.cestlavieuzice/" + R.drawable.sendvic_sa_prsutom)
            val imgPath: String = path.toString()
            dish = Dish(
                null, "Sendvic sa prsutom",
                null, "Pršuta + dodaci po izboru", true, true, 1, 270, 360,
                imgPath
            )
            action =
                SandwichesFragmentDirections.actionSandwichesFragmentToOverviewFragment(dish) as SandwichesFragmentDirections.ActionSandwichesFragmentToOverviewFragment
            mNavController.navigate(action)
        }
        mBinding.turkeyBreasts.setOnClickListener {
            path =
                Uri.parse("android.resource://com.nenad.cestlavieuzice/" + R.drawable.sendvic_sa_curecim_prsima)
            val imgPath: String = path.toString()
            dish = Dish(
                null, "Sendvič sa ćurecim prsima",
                null, "Ćureća prsa + dodaci po izboru", true, true, 1, 270, 360,
                imgPath
            )
            action =
                SandwichesFragmentDirections.actionSandwichesFragmentToOverviewFragment(dish) as SandwichesFragmentDirections.ActionSandwichesFragmentToOverviewFragment
            mNavController.navigate(action)
        }


    }

    private val clickTag = "__click__"
    fun View.blockingClickListener(debounceTime: Long = 1200L, action: () -> Unit) {
        this.setOnClickListener(object : View.OnClickListener {
            private var lastClickTime: Long = 0
            override fun onClick(v: View) {
                val timeNow = SystemClock.elapsedRealtime()
                val elapsedTimeSinceLastClick = timeNow - lastClickTime
                Log.d(
                    clickTag, """
                        DebounceTime: $debounceTime
                        Time Elapsed: $elapsedTimeSinceLastClick
                        Is within debounce time: ${elapsedTimeSinceLastClick < debounceTime}
                    """.trimIndent()
                )

                if (elapsedTimeSinceLastClick < debounceTime) {
                    Log.d(clickTag, "Double click shielded")
                    return
                } else {
                    Log.d(clickTag, "Click happened")
                    action()
                }
                lastClickTime = SystemClock.elapsedRealtime()
            }
        })
    }

}