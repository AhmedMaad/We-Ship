package com.maad.weship.shipping

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.maad.weship.R
import com.maad.weship.company.Shipment
import com.maad.weship.databinding.RequestListItemBinding
import com.ramotion.foldingcell.FoldingCell

class RequestsAdapter(
    val activity: Activity,
    val requests: ArrayList<Shipment>,
    val itemClickListener: ItemClickListener
) :
    RecyclerView.Adapter<RequestsAdapter.RequestVH>() {

    interface ItemClickListener {
        fun onItemClick(position: Int, cell: FoldingCell)
        fun onAcceptBtnClick(position: Int)
    }

    inner class RequestVH(val binding: RequestListItemBinding) : RecyclerView.ViewHolder(binding.root){
        init {
            binding.root.setOnClickListener { itemClickListener.onItemClick(adapterPosition, binding.foldingCell) }
            binding.acceptBtn.setOnClickListener { itemClickListener.onAcceptBtnClick(adapterPosition) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RequestVH(RequestListItemBinding.inflate(activity.layoutInflater))

    override fun onBindViewHolder(holder: RequestVH, position: Int) {
        holder.binding.descriptionTv.text = requests[position].generalDescription
        holder.binding.pickupPortTv.text = requests[position].pickupPort
        holder.binding.deliveryPortTv.text = requests[position].deliveryPort
        holder.binding.palettesNumberValueTv.text = requests[position].numberOfPalettes.toString()
        holder.binding.deliverBeforeValueTv.text = requests[position].deliverBefore
        holder.binding.companyNameTv.text = requests[position].companyName
        val pic = requests[position].companyPicture
        if (pic.isEmpty())
            holder.binding.profileIv.setImageResource(R.drawable.ic_person)
        else
            Glide.with(activity).load(pic).into(holder.binding.profileIv)
        holder.binding.weightTv.text = requests[position].weight.toString()
        holder.binding.typeValueTv.text = requests[position].containerType

/*        holder.binding.foldingCell.setOnClickListener {
            holder.binding.foldingCell.toggle(false)
        }

        holder.binding.acceptBtn.setOnClickListener {
            Toast.makeText(activity, "clicked!", Toast.LENGTH_SHORT).show();
        }*/

    }

    override fun getItemCount() = requests.size
}