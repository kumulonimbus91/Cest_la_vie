package com.nenad.cestlavieuzice.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nenad.cestlavieuzice.R
import com.nenad.cestlavieuzice.adapter.DishesAdapter
import com.nenad.cestlavieuzice.database.model.Dish
import com.nenad.cestlavieuzice.database.model.Order
import com.nenad.cestlavieuzice.databinding.FragmentOrderBinding
import com.nenad.cestlavieuzice.viewmodel.ViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderFragment : Fragment() {
    private lateinit var mBinding: FragmentOrderBinding
    lateinit var dishesAdapter: DishesAdapter
    lateinit var order: MutableList<Dish>
    val viewModel: ViewModel by activityViewModels<ViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_order, container, false)
        requireActivity().findViewById<ViewGroup>(R.id.ll_go).visibility = View.GONE






        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRv()
        setUpClickListeners()
        setUpClickListenerRv()
        requireActivity().findViewById<ViewGroup>(R.id.ll_go).visibility = View.GONE
        viewModel.dishes.observe(viewLifecycleOwner, Observer { dishes ->
            dishesAdapter.differ.submitList(dishes)
            var total: Int = 0
            for (i in 0 until dishes.size) {

                if (dishes[i].priceSmall != null) {
                    total += dishes[i].priceSmall!!
                } else if (dishes[i].priceBig != null) {
                    total += dishes[i].priceBig!!
                }


            }
            mBinding.fullPrice.text = total.toString()

            order = dishes.toMutableList()


        })


    }


    override fun onResume() {
        super.onResume()
        requireActivity().findViewById<ViewGroup>(R.id.ll_go).visibility = View.GONE
    }

    fun setUpRv() {
        dishesAdapter = DishesAdapter()
        mBinding.rvBasket.apply {
            adapter = dishesAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    fun setUpClickListeners() {
        mBinding.deleteAll.setOnClickListener {
            viewModel.deleteAllDishes()
        }
        mBinding.imgClose.setOnClickListener {
            this.findNavController().popBackStack()
        }

        mBinding.orderBtn.setOnClickListener {
            val order = Order(null, order, mBinding.fullPrice.text.toString().toInt())
            viewModel.insertOrder(order)
            viewModel.deleteAllDishes()
            Toast.makeText(requireContext(), "Porudzbina je spremna", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setUpClickListenerRv() {
        dishesAdapter.setOnClickListener(object : DishesAdapter.MyClickListener {

            override fun onDelete(p: Dish) {
                viewModel.deleteDish(p)
            }

            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

            }


        })


    }


}