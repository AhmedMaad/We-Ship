package com.maad.weship.company

import android.R
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.maad.weship.databinding.ActivityRegisterShipmentBinding
import com.maad.weship.general.ParentActivity

class RegisterShipmentActivity : ParentActivity(), DatePickerDialog.OnDateSetListener {

    private lateinit var binding: ActivityRegisterShipmentBinding
    val types = arrayOf("LCL", "FCL")
    var date = ""
    private lateinit var db: FirebaseFirestore
    private lateinit var companyId: String
    private lateinit var companyName: String
    private lateinit var companyPicture: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterShipmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = Firebase.firestore

        val pref = getSharedPreferences("settings", MODE_PRIVATE)
        companyId = pref.getString("id", null)!!

        db.collection("companies").document(companyId).get().addOnSuccessListener {
            companyName = it.getString("companyName")!!
            companyPicture = it.getString("image") ?: ""
        }

        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, types)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.containerTypeSpinner.adapter = adapter

        binding.dateTv.setOnClickListener {
            val dialog = DateChooser()
            dialog.show(supportFragmentManager, null)
        }

        binding.registerShipmentBtn.setOnClickListener {
            binding.progress.visibility = View.VISIBLE
            binding.registerShipmentBtn.visibility = View.INVISIBLE
            val palettes = binding.numberPalettesEt.text.toString().toInt()
            val weight = binding.weightEt.text.toString().toDouble()
            val description = binding.descriptionEt.text.toString()
            val pickupPort = binding.pickupPortEt.text.toString()
            val deliveryPort = binding.deliveryPortEt.text.toString()

            val shipment = Shipment(
                palettes,
                weight,
                description,
                pickupPort,
                deliveryPort,
                date,
                types[binding.containerTypeSpinner.selectedItemPosition],
                companyId,
                companyName,
                companyPicture
            )

            db.collection("shipments").add(shipment).addOnSuccessListener {
                it.update("requestId", it.id).addOnSuccessListener {
                    binding.progress.visibility = View.INVISIBLE
                    binding.registerShipmentBtn.visibility = View.VISIBLE
                    Toast.makeText(this, "Request Sent", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }

        }

    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        date = "$year-${month + 1}-$dayOfMonth"
        binding.dateTv.text = date
    }

}