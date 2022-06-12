package com.nenad.cestlavieuzice.view.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.nenad.cestlavieuzice.R
import com.nenad.cestlavieuzice.database.model.Dish
import com.nenad.cestlavieuzice.databinding.FragmentOverviewBinding
import com.nenad.cestlavieuzice.utils.InputFilterMinMax
import com.nenad.cestlavieuzice.viewmodel.ViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OverviewFragment : Fragment() {
    private lateinit var mBinding: FragmentOverviewBinding
    private val args: OverviewFragmentArgs by navArgs()
    var counter: Int = 1
    var ingredientsPrices: Int = 0
    var ingredients: ArrayList<String>? = null
    val viewModel: ViewModel by activityViewModels<ViewModel>()
    lateinit var mNavController: NavController
    var xBtnClicked: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_overview, container, false)

        requireActivity().findViewById<ViewGroup>(R.id.ll_go).visibility = View.GONE

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        counter = 1

        mBinding.amount.filters = arrayOf(InputFilterMinMax(1, 9))

        mNavController = findNavController()

        setUi()
        setOnClickListeners()
        setPrice()


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().findViewById<ViewGroup>(R.id.ll_go).visibility = View.GONE
    }

    override fun onDetach() {
        super.onDetach()
        requireActivity().findViewById<ViewGroup>(R.id.ll_go).visibility = View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        requireActivity().findViewById<ViewGroup>(R.id.ll_go).visibility = View.GONE
    }

    fun setOnClickListeners() {
        mBinding.xbtn.setOnClickListener {
            mBinding.xbtn.background =
                ContextCompat.getDrawable(requireActivity(), R.drawable.btn_selected)
            mBinding.xlbtn.background =
                ContextCompat.getDrawable(requireActivity(), R.drawable.btn_not_selected)

            xBtnClicked = true


            mBinding.price.text =
                ((args.dish.priceSmall.toString()
                    .toInt() * counter) + (ingredientsPrices)).toString()

        }
        mBinding.xlbtn.setOnClickListener {
            mBinding.xbtn.background =
                ContextCompat.getDrawable(requireActivity(), R.drawable.btn_not_selected)
            mBinding.xlbtn.background =
                ContextCompat.getDrawable(requireActivity(), R.drawable.btn_selected)

            xBtnClicked = false

            mBinding.price.text =
                ((args.dish.priceBig.toString().toInt() * counter) + (ingredientsPrices)).toString()

        }
        mBinding.addBtn.setOnClickListener {
            counter += 1
            mBinding.amount.setText(counter.toString())
            setPrice()
        }
        mBinding.subtractBtn.setOnClickListener {
            counter -= 1
            mBinding.amount.setText(counter.toString())
            setPrice()
        }
        mBinding.addToCart.setOnClickListener {
            val dish = Dish(
                null,
                args.dish.title,
                ingredients,
                args.dish.defaultIngredients,
                args.dish.hasSize,
                args.dish.hasIngredients,
                mBinding.amount.text.toString().toInt(),
                mBinding.price.text.toString().toInt() + ingredientsPrices,
                mBinding.price.text.toString().toInt() + ingredientsPrices,
                args.dish.urlToImage
            )
            viewModel.insertDish(dish)
        }
        mBinding.closeBtn.setOnClickListener {
            this.findNavController().popBackStack()
        }
        mBinding.fabCart.setOnClickListener {
            mNavController.navigate(R.id.action_overviewFragment_to_orderFragment)
        }
        mBinding.checkboxPomfrit.setOnClickListener {
            if (mBinding.checkboxPomfrit.isChecked) {
                ingredients?.add("Pomfrit")
                ingredientsPrices += 20
               setPrice()
            } else {
                ingredients?.remove("Pomfrit")
                ingredientsPrices -= 20
               setPrice()
            }
        }
        mBinding.checkboxTz.setOnClickListener {
            if (mBinding.checkboxTz.isChecked) {
                ingredients?.add("Caciki")
                ingredientsPrices += 30
                setPrice()
            } else {
                ingredients?.remove("Caciki")
                ingredientsPrices -= 30
                setPrice()
            }
        }
        mBinding.checkboxCucumber.setOnClickListener {
            if (mBinding.checkboxPomfrit.isChecked) {
                ingredients?.add("Krastavac")
            } else {
                ingredients?.remove("Krastavac")
            }
        }
        mBinding.checkboxKetchup.setOnClickListener {
            if (mBinding.checkboxKetchup.isChecked) {
                ingredients?.add("Kecap")
            } else {
                ingredients?.remove("Kecap")
            }
        }
        mBinding.checkboxMayo.setOnClickListener {
            if (mBinding.checkboxMayo.isChecked) {
                ingredients?.add("Majonez")
            } else {
                ingredients?.remove("Majonez")
            }
        }
        mBinding.checkboxTrapist.setOnClickListener {
           if (mBinding.checkboxTrapist.isChecked) {
               ingredients?.add("Trapist")
               ingredientsPrices += 30
               setPrice()
           } else {
               ingredients?.remove("Trapist")
               ingredientsPrices -= 30
               setPrice()
           }

        }
        mBinding.checkboxTomato.setOnClickListener {
            if (mBinding.checkboxTomato.isChecked) {
                ingredients?.add("Paradajiz")
            } else {
                ingredients?.add("Paradajiz")
            }
        }
        mBinding.checkboxSalad.setOnClickListener {
            if (mBinding.checkboxSalad.isChecked) {
                ingredients?.add("Zel.Salata")
            } else {
                ingredients?.add("Zel.Salata")
            }
        }
        mBinding.checkboxUrnebes.setOnClickListener {
            if (mBinding.checkboxSalad.isChecked) {
                ingredients?.add("Urnebes")
            } else {
                ingredients?.add("Urnebes")
            }
        }



    }

    fun setPrice() {
        if (args.dish.hasSize) {
            if (xBtnClicked) {
                mBinding.price.text =
                    ((args.dish.priceSmall.toString()
                        .toInt() * counter) + (ingredientsPrices)).toString()
            } else if (!xBtnClicked) {
                mBinding.price.text =
                    ((args.dish.priceBig.toString()
                        .toInt() * counter) + (ingredientsPrices)).toString()
            }

        } else {
            mBinding.price.text =
                ((args.dish.priceSmall.toString()
                    .toInt() * counter) + (ingredientsPrices)).toString()

        }
    }
    fun setUi() {
        Glide.with(this).load(args.dish.urlToImage).into(mBinding.imgDish)
        mBinding.tvTitle.text = args.dish.title
        mBinding.defaultingr.text = args.dish.defaultIngredients
        mBinding.price.text = args.dish.priceSmall.toString()
        if (args.dish.hasSize) {
            mBinding.llSizes.visibility = View.VISIBLE
            xBtnClicked = true
            mBinding.xbtn.background =
                ContextCompat.getDrawable(requireActivity(), R.drawable.btn_selected)
            mBinding.xlbtn.background =
                ContextCompat.getDrawable(requireActivity(), R.drawable.btn_not_selected)
        } else {
            mBinding.llSizes.visibility = View.INVISIBLE
        }

        if (args.dish.hasIngredients) {
            mBinding.llIngredients.visibility = View.VISIBLE
        } else {
            mBinding.llIngredients.visibility = View.INVISIBLE
        }
    }


}