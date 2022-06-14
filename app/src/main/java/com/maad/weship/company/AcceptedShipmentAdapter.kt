package com.maad.weship.company

import android.app.Activity
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maad.weship.databinding.AcceptedShipmentListItemBinding
import com.maad.weship.shipping.ShipmentRequest

class AcceptedShipmentAdapter(
    val activity: Activity,
    val requests: ArrayList<ShipmentRequest>,
    val itemClickListener: ItemClickListener
) : RecyclerView.Adapter<AcceptedShipmentAdapter.MVH>() {

    interface ItemClickListener {
        fun onItemClick(position: Int)
    }

    inner class MVH(val binding: AcceptedShipmentListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener { itemClickListener.onItemClick(adapterPosition) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MVH(AcceptedShipmentListItemBinding.inflate(activity.layoutInflater))

    override fun onBindViewHolder(holder: MVH, position: Int) {
        holder.binding.tv.text =
            "Company ${requests[position].companyName} accepted your request about: \n ${requests[position].request.generalDescription}.\n\nOffered Price: ${requests[position].price}."
    }

    override fun getItemCount() = requests.size

}