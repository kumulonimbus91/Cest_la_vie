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
import androidx.navigation.fragment.navArgs
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
    val args: OrderFragmentArgs? by navArgs()

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

       val orderAgain: OrderFragmentArgs = OrderFragmentArgs.fromBundle(requireArguments())

        val orderAg = arguments?.getParcelable<Order>("order")

      if (orderAg?.dishes == null) {
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

              if (dishes.isEmpty()) {
                  mBinding.etEmpty.visibility = View.VISIBLE
                  mBinding.rvBasket.visibility = View.INVISIBLE
              } else {
                  mBinding.etEmpty.visibility = View.INVISIBLE
                  mBinding.rvBasket.visibility = View.VISIBLE
              }


          })

      } else {
          dishesAdapter.differ.submitList(orderAg.dishes)
          var total: Int = 0
          for (i in 0 until orderAg.dishes.size) {
              if (orderAg.dishes[i].priceSmall != null) {
                  total += orderAg.dishes[i].priceSmall!!
              } else if (orderAg.dishes[i].priceBig != null) {
                  total += orderAg.dishes[i].priceBig!!
              }
          }
          order = orderAg.dishes.toMutableList()
          mBinding.fullPrice.text = total.toString()
          viewModel.deleteOrder(orderAg)
      }

        requireActivity().findViewById<ViewGroup>(R.id.ll_go).visibility = View.GONE








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