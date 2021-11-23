package com.josus.shoppingapp.util

import android.content.Context
import android.provider.Settings.Global.getString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.josus.shoppingapp.R
import com.josus.shoppingapp.data.model.Product

class ListAdapter(context: Context) : RecyclerView.Adapter<ListAdapter.ProductViewHolder>() {
    inner class ProductViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)

    private val differCallback = object :DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_product,parent,false)
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = differ.currentList[position]

        holder.itemView.apply {
            Glide.with(this).load(product.image).placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(this.findViewById(R.id.product_image))
            this.findViewById<TextView>(R.id.product_title).text = product.title
            this.findViewById<TextView>(R.id.product_category).text = product.category
            this.findViewById<TextView>(R.id.product_price).text = context.getString(R.string.product_price_txt,product.price.toString())
        }
    }

    override fun getItemCount(): Int {
       return differ.currentList.size
    }
}