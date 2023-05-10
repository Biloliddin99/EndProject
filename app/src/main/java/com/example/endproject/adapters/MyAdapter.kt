package com.example.endproject.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.endproject.databinding.ItemRvBinding
import com.example.endproject.models.User

class MyAdapter(val context: Context, var list: ArrayList<User>) : RecyclerView.Adapter<MyAdapter.Vh>() {

    inner class Vh(private val itemRvBinding: ItemRvBinding) :
        RecyclerView.ViewHolder(itemRvBinding.root) {

        fun onBind(user: User) {
           itemRvBinding.tvName.text = user.name
            itemRvBinding.tvDate.text = user.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

}