package com.nenad.cestlavieuzice.view.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.common.collect.Lists.reverse
import com.nenad.cestlavieuzice.R
import com.nenad.cestlavieuzice.adapter.OrdersAdapter
import com.nenad.cestlavieuzice.database.model.Order
import com.nenad.cestlavieuzice.databinding.FragmentHistoryBinding
import com.nenad.cestlavieuzice.viewmodel.ViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import java.util.Collections.reverse

@AndroidEntryPoint
class HistoryFragment : Fragment() {

    lateinit var mBinding: FragmentHistoryBinding
    lateinit var ordersAdapter: OrdersAdapter
    val viewModel: ViewModel by activityViewModels<ViewModel>()
    lateinit var mNavController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false)
        requireActivity().findViewById<ViewGroup>(R.id.ll_go).visibility = View.GONE

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mNavController = findNavController()
        setUpRv()
        setUpOnClickListeners()

        viewModel.orders.observe(viewLifecycleOwner, {
            ordersAdapter.differ.submitList(it)


        })


    }


    override fun onDestroy() {
        super.onDestroy()
        requireActivity().findViewById<ViewGroup>(R.id.ll_go).visibility = View.VISIBLE
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().findViewById<ViewGroup>(R.id.ll_go).visibility = View.GONE
    }


    fun setUpRv() {
        ordersAdapter = OrdersAdapter()
        mBinding.rvOrders.apply {
            adapter = ordersAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    fun setUpOnClickListeners() {
        mBinding.backBtn.setOnClickListener {
            //val action = HistoryFragmentDirections.actionHistoryFragmentToPizzaFragment()
            mNavController.navigate(R.id.pizzaFragment)
        }
        ordersAdapter.setOnClickListener(object : OrdersAdapter.MyClickListener {
            override fun onNavigate(p: Order?) {
                val action = HistoryFragmentDirections.actionHistoryFragmentToOrderFragment()
                val bundle = bundleOf("order" to p)

                findNavController().navigate(R.id.action_historyFragment_to_orderFragment, bundle)


            }

            override fun onDelete(p: Order) {
                viewModel.deleteOrder(p)
            }

            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

            }

        })
    }


}