package com.maad.weship.company

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.maad.weship.databinding.ActivityPaymentBinding
import com.maad.weship.general.ParentActivity
import com.maad.weship.shipping.ShipmentRequest

class PaymentActivity : ParentActivity() {

    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = Firebase.firestore

        val request = intent.getParcelableExtra<ShipmentRequest>("request")!!

        binding.cashCv.setOnClickListener {
            val map = HashMap<String, String>()
            map["status"] = "History"
            db.collection("shipmentRequestStatus").document(request.shipmentRequestId)
                .update(map as Map<String, String>).addOnSuccessListener {
                    Toast.makeText(this, "Thank You!", Toast.LENGTH_SHORT).show()
                }
        }

        binding.visaCv.setOnClickListener {
            val i = Intent(this, VisaActivity::class.java)
            i.putExtra("request", request)
            startActivity(i)
        }

        //TODO: add rating in design

    }

}