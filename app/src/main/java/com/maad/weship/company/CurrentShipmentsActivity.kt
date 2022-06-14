package com.maad.weship.company

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.maad.weship.R
import com.maad.weship.databinding.ActivityCurrentShipmentsBinding
import com.maad.weship.general.ParentActivity
import com.maad.weship.shipping.ShipmentRequest

class CurrentShipmentsActivity : ParentActivity(), AcceptedShipmentAdapter.ItemClickListener {

    private lateinit var db: FirebaseFirestore
    private lateinit var binding: ActivityCurrentShipmentsBinding
    private var requests = arrayListOf<ShipmentRequest>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCurrentShipmentsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = Firebase.firestore
    }

    override fun onResume() {
        super.onResume()
        binding.progress.visibility = View.VISIBLE
        val pref = getSharedPreferences("settings", MODE_PRIVATE)
        val companyId = pref.getString("id", null)!!

        db.collection("shipmentRequestStatus").get().addOnSuccessListener {
            requests = arrayListOf<ShipmentRequest>()
            requests.clear()
            val all = it.toObjects(ShipmentRequest::class.java)
            for (request in all)
                if (request.status == "Pending" && companyId == request.request.companyId)
                    requests.add(request)

            if (requests.isEmpty()) {
                Toast.makeText(this, "No Requests available", Toast.LENGTH_SHORT).show();
                binding.acceptedRequestsRv.visibility = View.INVISIBLE
            } else {
                binding.acceptedRequestsRv.visibility = View.VISIBLE
                val divider =
                    DividerItemDecoration(
                        binding.acceptedRequestsRv.context,
                        DividerItemDecoration.VERTICAL
                    )
                divider.setDrawable(
                    ContextCompat.getDrawable(
                        baseContext,
                        R.drawable.line_divider
                    )!!
                )
                binding.acceptedRequestsRv.addItemDecoration(divider)
                val adapter = AcceptedShipmentAdapter(this, requests, this)
                binding.acceptedRequestsRv.adapter = adapter
            }
            binding.progress.visibility = View.INVISIBLE

        }
    }

    override fun onItemClick(position: Int) {
        val i = Intent(this, PaymentActivity::class.java)
        i.putExtra("request", requests[position])
        startActivity(i)
    }

}