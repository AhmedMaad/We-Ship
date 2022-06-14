package com.maad.weship.company

import android.app.Activity
import android.content.Intent
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maad.weship.databinding.AcceptedShipmentListItemBinding
import com.maad.weship.shipping.ShipmentRequest

class AcceptedShipmentAdapter(val activity: Activity, val requests: ArrayList<ShipmentRequest>) :
    RecyclerView.Adapter<AcceptedShipmentAdapter.MVH>() {

    class MVH(val binding: AcceptedShipmentListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MVH(AcceptedShipmentListItemBinding.inflate(activity.layoutInflater))

    override fun onBindViewHolder(holder: MVH, position: Int) {
        holder.binding.tv.text =
            "Company ${requests[position].companyName} accepted your request about: \n ${requests[position].request.generalDescription}.\nOffered Price: ${requests[position].price}."

        holder.binding.tv.setOnClickListener {
            val i = Intent(activity, PaymentActivity::class.java)
            i.putExtra("request", requests[position])
            activity.startActivity(i)
        }

    }

    override fun getItemCount() = requests.size

}