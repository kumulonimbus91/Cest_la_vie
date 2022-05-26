package com.nenad.cestlavieuzice.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.FtsOptions
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
    var total: Int? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_order, container, false)







        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRv()
        setUpClickListeners()
        viewModel.dishes.observe(viewLifecycleOwner, Observer { dishes ->
            dishesAdapter.differ.submitList(dishes)

            for (i in 0 until dishesAdapter.itemCount) {
                total = total?.plus(dishes.get(i).priceSmall!!)
            }
            mBinding.fullPrice.text = total.toString()
            order = dishes.toMutableList()


        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().findViewById<ViewGroup>(R.id.ll_go).visibility = View.GONE
    }

    override fun onDetach() {
        super.onDetach()
        requireActivity().findViewById<ViewGroup>(R.id.ll_go).visibility = View.VISIBLE
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
        mBinding.orderBtn.setOnClickListener {
            val order = Order(null, order, total)
            viewModel.insertOrder(order)
            viewModel.deleteAllDishes()
            Toast.makeText(requireContext(), "Porudzbina je spremna", Toast.LENGTH_SHORT).show()
        }
    }


}