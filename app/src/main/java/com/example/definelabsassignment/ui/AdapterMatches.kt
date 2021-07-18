package com.example.definelabsassignment.ui

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.definelabsassignment.MyPreferenses
import com.example.definelabsassignment.R
import com.example.definelabsassignment.databinding.RvMatchItemBinding
import com.example.definelabsassignment.network.responses.VenuesItem

class AdapterMatches constructor(
    private val context: Context,
    private val listener: MatchesListener,
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context),
) : RecyclerView.Adapter<AdapterMatches.ViewHolder>() {

    private lateinit var list: List<VenuesItem>

    class ViewHolder(val rvMatchItemBinding: RvMatchItemBinding) :
        RecyclerView.ViewHolder(rvMatchItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RvMatchItemBinding.inflate(layoutInflater))

    }

    fun refresh(list: List<VenuesItem>) {
        this.list = list
        notifyDataSetChanged()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val response = list[position].also { holder.rvMatchItemBinding.response = it }

        val ids: List<String> =
            MyPreferenses(context).getString("venueids", "")!!.split(",")
        for (i in ids) {
            if (i == response.venue_id) {
                holder.rvMatchItemBinding.imgStar.setImageDrawable(
                    context.resources.getDrawable(
                        R.drawable.ic_star_active,
                        context.theme
                    )
                )
                break
            } else {
                holder.rvMatchItemBinding.imgStar.setImageDrawable(
                    context.resources.getDrawable(
                        R.drawable.ic_star_inactive,
                        context.theme
                    )
                )
            }
        }

        holder.rvMatchItemBinding.imgStar.setOnClickListener {
            listener.onSelect(
                holder.rvMatchItemBinding.imgStar,
                response.venue_id,
                response.name,
                response.location?.address
            )
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface MatchesListener {
        fun onSelect(img: AppCompatImageView, id: String?, name: String?, address: String?)
    }
}