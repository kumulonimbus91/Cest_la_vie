package com.nenad.cestlavieuzice.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nenad.cestlavieuzice.database.model.Dish
import com.nenad.cestlavieuzice.database.model.Order
import com.nenad.cestlavieuzice.databinding.DishitemBinding
import com.nenad.cestlavieuzice.databinding.DishitemordersBinding

class OrdersAdapter() : RecyclerView.Adapter<OrdersAdapter.ViewHolder>() {


    private val diffCallBack = object : DiffUtil.ItemCallback<Order>() {
        override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean {
            return oldItem == newItem
        }
    }
    var differ = AsyncListDiffer(this, diffCallBack)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersAdapter.ViewHolder {

        return ViewHolder.from(parent)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val order = differ.currentList[position]


        holder.bind(order)

        holder.itemView.apply {
            Glide.with(this).load(order.dishes[1]).into(holder.binding.img)
            holder.binding.amountFirst.text = order.dishes[1].numOfItems.toString()
            holder.binding.amountSecond.text = order.dishes[2].numOfItems.toString()
            holder.binding.amountThird.text = order.dishes[3].numOfItems.toString()
            holder.binding.titleFirst.text = order.dishes[1].title
            holder.binding.titleSecond.text = order.dishes[2].title
            holder.binding.titleThird.text = order.dishes[3].title

            holder.binding.price.text = order.dishes.all {
                it.priceSmall.toString().toBoolean()
            }.toString()




        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class ViewHolder constructor(
        val binding: DishitemordersBinding
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {


        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)

                val binding = DishitemordersBinding.inflate(layoutInflater, parent, false)




                return ViewHolder(binding)
            }
        }

        fun bind(item: Order) {


        }


        override fun onClick(p0: View?) {

        }


    }


}