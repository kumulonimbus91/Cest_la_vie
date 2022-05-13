package com.nenad.cestlavieuzice.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.nenad.cestlavieuzice.R
import com.nenad.cestlavieuzice.databinding.FragmentOverviewBinding
import com.nenad.cestlavieuzice.utils.InputFilterMinMax


class OverviewFragment : Fragment() {
    private lateinit var mBinding: FragmentOverviewBinding
    val args: OverviewFragmentArgs by navArgs()
    var counter: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_overview, container, false)

        val dish = OverviewFragmentArgs.fromBundle(requireArguments())

        mBinding.xbtn.background = ContextCompat.getDrawable(requireActivity(), R.drawable.btn_selected)
        mBinding.price.text = args.dish.priceSmall.toString()
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

        return mBinding.root
    }

    fun setOnClickListeners() {
        mBinding.xbtn.setOnClickListener {
            mBinding.xbtn.background = ContextCompat.getDrawable(requireActivity(), R.drawable.btn_selected)
            mBinding.xlbtn.background = ContextCompat.getDrawable(requireActivity(), R.drawable.btn_not_selected)

            mBinding.price.text = args.dish.priceSmall.toString()

        }
        mBinding.xlbtn.setOnClickListener {
            mBinding.xbtn.background = ContextCompat.getDrawable(requireActivity(), R.drawable.btn_not_selected)
            mBinding.xlbtn.background = ContextCompat.getDrawable(requireActivity(), R.drawable.btn_selected)



            mBinding.price.text = args.dish.priceBig.toString()
        }
        mBinding.addBtn.setOnClickListener {
            //var counter: Int = 1
            counter = counter + 1

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
            counter = counter - 1

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
    }


}