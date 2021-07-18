package com.example.definelabsassignment.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.definelabsassignment.databinding.RvMatchItemBinding
import com.example.definelabsassignment.databinding.RvSavedMatchesItemBinding
import com.example.definelabsassignment.network.local.entity.VenuesEntity
import com.example.definelabsassignment.network.responses.VenuesItem

class AdapterSavedMatches constructor(
    private val context: Context,
    private val listener: MatchesListener,
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context),
) : RecyclerView.Adapter<AdapterSavedMatches.ViewHolder>() {

    private lateinit var list: List<VenuesEntity>

    class ViewHolder(val rvMatchItemBinding: RvSavedMatchesItemBinding) :
        RecyclerView.ViewHolder(rvMatchItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RvSavedMatchesItemBinding.inflate(layoutInflater))

    }

    fun refresh(list: List<VenuesEntity>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val response = list[position].also { holder.rvMatchItemBinding.response = it }

        holder.rvMatchItemBinding.imgStar.setOnClickListener {
            listener.onSelect(response.venue_id, response.name, response.address)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface MatchesListener {
        fun onSelect(id: String?, name: String?, address: String?)
    }
}