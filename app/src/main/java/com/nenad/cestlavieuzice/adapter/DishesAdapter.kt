package com.nenad.cestlavieuzice.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nenad.cestlavieuzice.database.model.Dish
import com.nenad.cestlavieuzice.databinding.DishitemBinding

class DishesAdapter() : RecyclerView.Adapter<DishesAdapter.ViewHolder>() {


    private val diffCallBack = object : DiffUtil.ItemCallback<Dish>() {
        override fun areItemsTheSame(oldItem: Dish, newItem: Dish): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Dish, newItem: Dish): Boolean {
            return oldItem == newItem
        }
    }
    var differ = AsyncListDiffer(this, diffCallBack)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishesAdapter.ViewHolder {

        return ViewHolder.from(parent)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val dish = differ.currentList[position]


        holder.bind(dish)

        holder.itemView.apply {
            Glide.with(this).load(dish.urlToImage).into(holder.binding.img)
            holder.binding.titleTv.text = dish.title
            holder.binding.tvAmount.text = dish.numOfItems.toString()

            if (dish.hasSize) {
                holder.binding.priceTv.text = dish.priceBig.toString()
            } else {
                holder.binding.priceTv.text = dish.priceSmall.toString()
            }


        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class ViewHolder constructor(
        val binding: DishitemBinding
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {


        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)

                val binding = DishitemBinding.inflate(layoutInflater, parent, false)




                return ViewHolder(binding)
            }
        }

        fun bind(item: Dish) {


        }


        override fun onClick(p0: View?) {

        }


    }
}