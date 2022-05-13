package com.nenad.cestlavieuzice.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.nenad.cestlavieuzice.R
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
    var ingredients: Array<String>? = null

    val viewModel: ViewModel by activityViewModels<ViewModel>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_overview, container, false)



        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dish = OverviewFragmentArgs.fromBundle(requireArguments())

        mBinding.xbtn.background = ContextCompat.getDrawable(requireActivity(), R.drawable.btn_selected)
        counter = 1
        mBinding.price.text = (args.dish.priceSmall.toString().toInt() * counter).toString()
        mBinding.amount.filters = arrayOf(InputFilterMinMax(0,9))



        setOnClickListeners()

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



    fun onCheckboxClicked(view: View) {
        if (view is CheckBox) {
            val checked: Boolean = view.isChecked

            when (view.id) {
                mBinding.checkboxKetchup.id -> {
                    if (checked) {
                        // Put some meat on the sandwich
                    } else {
                        // Remove the meat
                    }
                }
                mBinding.checkboxMayo.id -> {
                    if (checked) {
                        // Cheese me
                    } else {
                        // I'm lactose intolerant
                    }
                }
                mBinding.checkboxCucumber.id -> {
                    if (checked) {

                    } else {

                    }
                }
                mBinding.checkboxPomfrit.id -> {
                    if (checked) {
                             ingredientsPrices += 20


                    } else {

                    }
                }
                mBinding.checkboxTz.id -> {
                    if (checked) {

                    } else {

                    }
                }
                mBinding.checkboxTrapist.id -> {
                    if (checked) {

                    } else {

                    }
                }
                mBinding.checkboxCucumber.id -> {
                    if (checked) {

                    } else {

                    }
                }
                mBinding.checkboxTomato.id -> {
                    if (checked) {

                    } else {

                    }
                }
                mBinding.checkboxUrnebes.id -> {
                    if (checked) {

                    } else {

                    }
                }
                mBinding.checkboxSalad.id -> {
                    if (checked) {

                    } else {

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
//            if (args.dish.hasSize) {
//                if (mBinding.xbtn.isSelected) {
//                    mBinding.price.text = (args.dish.priceSmall.toString().toInt() * counter).toString()
//                } else if (mBinding.xlbtn.isSelected) {
//                    mBinding.price.text = (args.dish.priceBig.toString().toInt() * counter).toString()
//                }
//            } else {
//                mBinding.price.text = (args.dish.priceSmall.toString().toInt() * counter).toString()
//            }

        }
        mBinding.subtractBtn.setOnClickListener {
            //var counter: Int = 1
            counter -= 1

            mBinding.amount.setText(counter.toString())

//            if (args.dish.hasSize) {
//                if (mBinding.xbtn.isSelected) {
//                    mBinding.price.text = (args.dish.priceSmall.toString().toInt() * counter).toString()
//                } else if (mBinding.xlbtn.isSelected) {
//                    mBinding.price.text = (args.dish.priceBig.toString().toInt() * counter).toString()
//                }
//            } else {
//                mBinding.price.text = (args.dish.priceSmall.toString().toInt() * counter).toString()
//            }




        }
        mBinding.addToCart.setOnClickListener {

        }
    }


}