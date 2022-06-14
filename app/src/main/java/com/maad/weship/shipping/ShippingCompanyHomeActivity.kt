package com.maad.weship.shipping

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.maad.weship.R
import com.maad.weship.company.Shipment
import com.maad.weship.databinding.ActivityShippingCompanyHomeBinding
import com.maad.weship.general.ParentActivity
import com.maad.weship.general.ProfileActivity
import com.ramotion.foldingcell.FoldingCell


class ShippingCompanyHomeActivity : ParentActivity(), RequestsAdapter.ItemClickListener {

    private lateinit var db: FirebaseFirestore
    private lateinit var binding: ActivityShippingCompanyHomeBinding
    private lateinit var uId: String
    private lateinit var allShipments: ArrayList<Shipment>
    private var managedShipments = arrayListOf<ShipmentRequest>()
    private lateinit var adapter: RequestsAdapter
    private var toBeShownRequests = arrayListOf<Shipment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShippingCompanyHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = Firebase.firestore

        val prefs = getSharedPreferences("settings", MODE_PRIVATE)
        uId = prefs.getString("id", null)!!

        db
            .collection("shipmentRequestStatus")
            .get()
            .addOnSuccessListener {
                val statusRequests = it.toObjects(ShipmentRequest::class.java)
                for (status in statusRequests)
                    if (status.userId == uId) {
                        managedShipments.add(status)
                        Log.d("trace", "Repeated request")
                    }
                fetchAllShipmentRequests()
            }

    }

    private fun fetchAllShipmentRequests() {

        db.collection("shipments").get().addOnSuccessListener {
            allShipments = it.toObjects(Shipment::class.java) as ArrayList<Shipment>
            for (shipment in allShipments) {
                var flag = false
                for (managedShipment in managedShipments) {
                    if (shipment.requestId == managedShipment.request.requestId) {
                        Log.d("trace", "Final repetition")
                        flag = true
                    }
                }
                if (!flag) {
                    Log.d("trace", "Adding Request after filtration")
                    toBeShownRequests.add(shipment)
                }
            }

            if (toBeShownRequests.isEmpty())
                Toast.makeText(this, "No requests available", Toast.LENGTH_SHORT).show();
            else {
                val divider =
                    DividerItemDecoration(
                        binding.requestsRv.context,
                        DividerItemDecoration.VERTICAL
                    )
                divider.setDrawable(
                    ContextCompat.getDrawable(
                        baseContext,
                        R.drawable.line_divider
                    )!!
                )
                binding.requestsRv.addItemDecoration(divider)
                adapter = RequestsAdapter(this, toBeShownRequests, this)
                binding.requestsRv.adapter = adapter
            }
            binding.progress.visibility = View.INVISIBLE

        }

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val item = menu!!.add("Profile")
        item.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_NEVER)
        item.setOnMenuItemClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
            return@setOnMenuItemClickListener true
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onItemClick(position: Int, cell: FoldingCell) {
        cell.toggle(false)
    }

    override fun onAcceptBtnClick(position: Int) {

        val v = layoutInflater.inflate(R.layout.price_layout, null)
        val priceET: EditText = v.findViewById(R.id.price_et)
        val builder = AlertDialog.Builder(this)
        builder
            .setView(v)
            .setPositiveButton("Send") { _, _ ->

                binding.progress.visibility = View.VISIBLE
                val price = priceET.text.toString().toDouble()
                val requestStatus = ShipmentRequest(toBeShownRequests[position], uId, price)
                db
                    .collection("shipmentRequestStatus")
                    .add(requestStatus)
                    .addOnSuccessListener {
                        toBeShownRequests.removeAt(position)
                        adapter.notifyItemRemoved(position)
                        binding.progress.visibility = View.GONE
                        Toast.makeText(this, "Price Sent", Toast.LENGTH_SHORT).show()
                    }

            }
            .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
            .show()


    }

}