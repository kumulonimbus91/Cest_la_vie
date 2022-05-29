package com.nenad.cestlavieuzice.view.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.nenad.cestlavieuzice.R
import com.nenad.cestlavieuzice.adapter.DishesAdapter
import com.nenad.cestlavieuzice.adapter.OrdersAdapter
import com.nenad.cestlavieuzice.databinding.FragmentHistoryBinding
import com.nenad.cestlavieuzice.viewmodel.ViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Observer

@AndroidEntryPoint
class HistoryFragment : Fragment() {

    lateinit var mBinding: FragmentHistoryBinding
    lateinit var ordersAdapter: OrdersAdapter
    val viewModel: ViewModel by activityViewModels<ViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false)


        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRv()

        viewModel.orders.observe(viewLifecycleOwner, {
            ordersAdapter.differ.submitList(it)


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
        ordersAdapter = OrdersAdapter()
        mBinding.rvOrders.apply {
            adapter = ordersAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }


}