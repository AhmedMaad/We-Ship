package com.maad.weship.company

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.maad.weship.R
import com.maad.weship.shipping.ShipmentRequest

class VisaActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visa)
        db = Firebase.firestore

        val request = intent.getParcelableExtra<ShipmentRequest>("request")!!

        val visaPay: Button = findViewById(R.id.pay_btn)
        visaPay.setOnClickListener {
            val map = HashMap<String, String>()
            map["status"] = "History"
            db.collection("shipmentRequestStatus").document(request.shipmentRequestId)
                .update(map as Map<String, String>).addOnSuccessListener {
                    Toast.makeText(this, "Thank You!", Toast.LENGTH_SHORT).show()
                }
        }

    }
}