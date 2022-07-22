package com.example.roomdbkotlin.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdbkotlin.R
import com.example.roomdbkotlin.model.User
import kotlinx.android.synthetic.main.custome_list.view.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custome_list,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.itemView.tvSLno.text = currentItem.id.toString()
        holder.itemView.tvFirstName.text = currentItem.firstName.toString()
        holder.itemView.tvLastName.text = currentItem.lastname.toString()
        holder.itemView.tvAge.text = currentItem.age.toString()

        holder.itemView.setOnClickListener{
            val action = ListFragmentDirections.listFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
       return userList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    fun setData(user: List<User>){
        this.userList = user
        notifyDataSetChanged()
    }
}