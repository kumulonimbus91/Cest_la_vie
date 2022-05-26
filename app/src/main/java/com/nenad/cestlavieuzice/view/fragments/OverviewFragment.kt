package com.nenad.cestlavieuzice.view.fragments

import android.content.Context
import android.os.Bundle
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
    val args: OverviewFragmentArgs by navArgs()
    var counter: Int = 1
    var ingredientsPrices: Int = 0
    var ingredients: ArrayList<String>? = null
    val viewModel: ViewModel by activityViewModels<ViewModel>()
    lateinit var mNavController: NavController



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_overview, container, false)

        requireActivity().findViewById<ViewGroup>(R.id.ll_go).visibility = View.GONE

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dish = OverviewFragmentArgs.fromBundle(requireArguments())

        mBinding.xbtn.background = ContextCompat.getDrawable(requireActivity(), R.drawable.btn_selected)
        counter = 1
        mBinding.price.text = (args.dish.priceSmall.toString().toInt() * counter).toString()
        mBinding.amount.filters = arrayOf(InputFilterMinMax(0,9))


        mNavController = findNavController()

        setOnClickListeners()
        onCheckboxClicked(View(requireContext()))


        Glide.with(this).load(dish.dish.urlToImage).into(mBinding.imgDish)
        mBinding.tvTitle.text = dish.dish.title
        mBinding.defaultingr.text = dish.dish.defaultIngredients
        mBinding.price.text = dish.dish.priceSmall.toString()

        if (dish.dish.hasSize) {
            mBinding.llSizes.visibility = View.VISIBLE
        } else {
            mBinding.llSizes.visibility = View.INVISIBLE
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().findViewById<ViewGroup>(R.id.ll_go).visibility = View.GONE
    }

    override fun onDetach() {
        super.onDetach()
        requireActivity().findViewById<ViewGroup>(R.id.ll_go).visibility = View.VISIBLE
    }



    fun onCheckboxClicked(view: View) {
        if (view is CheckBox) {
            val checked: Boolean = view.isChecked

            when (view.id) {
                mBinding.checkboxKetchup.id -> {
                    if (checked) {
                        ingredients?.add("Kecap")
                    } else {
                        ingredients?.remove("Kecap")
                    }
                }
                mBinding.checkboxMayo.id -> {
                    if (checked) {
                        ingredients?.add("Majonez")
                    } else {
                        ingredients?.remove("Majonez")
                    }
                }
                mBinding.checkboxCucumber.id -> {
                    if (checked) {
                        ingredients?.add("Krastavac")
                    } else {
                        ingredients?.remove("Krastavac")
                    }
                }
                mBinding.checkboxPomfrit.id -> {
                    if (checked) {
                        ingredients?.add("Pomfrit")
                        ingredientsPrices += 20


                    } else {
                        ingredientsPrices -= 20
                        ingredients?.remove("Pomfrit")

                    }
                }
                mBinding.checkboxTz.id -> {
                    if (checked) {
                        ingredientsPrices += 30
                        ingredients?.add("Caciki")

                    } else {
                        ingredientsPrices -= 30
                        ingredients?.remove("Caciki")
                    }
                }
                mBinding.checkboxTrapist.id -> {
                    if (checked) {
                        ingredients?.add("Trapist")

                    } else {
                        ingredients?.remove("Trapist")
                    }
                }

                mBinding.checkboxTomato.id -> {
                    if (checked) {
                        ingredients?.add("Paradajz")
                    } else {
                        ingredients?.remove("Paradajz")
                    }
                }
                mBinding.checkboxUrnebes.id -> {
                    if (checked) {
                        ingredients?.add("Urnebes")

                    } else {
                        ingredients?.remove("Urnebes")
                    }
                }
                mBinding.checkboxSalad.id -> {
                    if (checked) {
                        ingredients?.add("Zel. salata")

                    } else {
                        ingredients?.remove("Zel. salata")
                    }
                }

            }
        }
    }

    fun setOnClickListeners() {
        mBinding.xbtn.setOnClickListener {
            mBinding.xbtn.background = ContextCompat.getDrawable(requireActivity(), R.drawable.btn_selected)
            mBinding.xlbtn.background = ContextCompat.getDrawable(requireActivity(), R.drawable.btn_not_selected)

            mBinding.price.text = (args.dish.priceSmall.toString().toInt() * counter).toString()

        }
        mBinding.xlbtn.setOnClickListener {
            mBinding.xbtn.background = ContextCompat.getDrawable(requireActivity(), R.drawable.btn_not_selected)
            mBinding.xlbtn.background = ContextCompat.getDrawable(requireActivity(), R.drawable.btn_selected)



            mBinding.price.text = (args.dish.priceBig.toString().toInt() * counter).toString()
        }
        mBinding.addBtn.setOnClickListener {
            //var counter: Int = 1
            counter += 1

            mBinding.amount.setText(counter.toString())
            if (args.dish.hasSize) {
                if (mBinding.xbtn.isSelected) {
                    mBinding.price.text = (args.dish.priceSmall.toString().toInt() * counter).toString()
                } else if (mBinding.xlbtn.isSelected) {
                    mBinding.price.text = (args.dish.priceBig.toString().toInt() * counter).toString()
                }
            } else {
                mBinding.price.text = (args.dish.priceSmall.toString().toInt() * counter).toString()
            }

        }
        mBinding.subtractBtn.setOnClickListener {
            //var counter: Int = 1
            counter -= 1

            mBinding.amount.setText(counter.toString())

            if (args.dish.hasSize) {
                if (mBinding.xbtn.isSelected) {
                    mBinding.price.text = (args.dish.priceSmall.toString().toInt() * counter).toString()
                } else if (mBinding.xlbtn.isSelected) {
                    mBinding.price.text = (args.dish.priceBig.toString().toInt() * counter).toString()
                }
            } else {
                mBinding.price.text = (args.dish.priceSmall.toString().toInt() * counter).toString()
            }




        }
        mBinding.addToCart.setOnClickListener {

            val dish = Dish(null, args.dish.title, ingredients, args.dish.defaultIngredients, args.dish.hasSize, args.dish.hasIngredients,
                mBinding.amount.text.toString().toInt(),
                mBinding.price.text.toString().toInt() + ingredientsPrices,mBinding.price.text.toString().toInt() + ingredientsPrices, args.dish.urlToImage)


            viewModel.insertDish(dish)
        }
        mBinding.closeBtn.setOnClickListener {
            findNavController().navigate(R.id.pizzaFragment)
            requireActivity().findViewById<ViewGroup>(R.id.ll_go).visibility = View.VISIBLE
        }
        mBinding.fabCart.setOnClickListener {


            mNavController.navigate(R.id.action_overviewFragment_to_orderFragment)
        }
    }


}