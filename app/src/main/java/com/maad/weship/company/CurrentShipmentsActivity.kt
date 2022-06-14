package com.maad.weship.company

import androidx.appcompat.app.AppCompatActivity
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

class CurrentShipmentsActivity : ParentActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var binding: ActivityCurrentShipmentsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCurrentShipmentsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = Firebase.firestore
    }

    override fun onResume() {
        super.onResume()
        val pref = getSharedPreferences("settings", MODE_PRIVATE)
        val companyId = pref.getString("id", null)!!

        db.collection("shipmentRequestStatus").get().addOnSuccessListener {
            val requests = arrayListOf<ShipmentRequest>()
            val all = it.toObjects(ShipmentRequest::class.java)
            for (request in all)
                if (request.status == "Pending" && companyId == request.request.companyId)
                    requests.add(request)

            if (requests.isEmpty())
                Toast.makeText(this, "No Requests available", Toast.LENGTH_SHORT).show();
            else {
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
                val adapter = AcceptedShipmentAdapter(this, requests)
                binding.acceptedRequestsRv.adapter = adapter
            }
            binding.progress.visibility = View.INVISIBLE

        }
    }

}