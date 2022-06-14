package com.maad.weship.company

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.maad.weship.databinding.ActivityPaymentBinding
import com.maad.weship.general.Company
import com.maad.weship.general.ParentActivity
import com.maad.weship.shipping.ShipmentRequest

class PaymentActivity : ParentActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var companyId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = Firebase.firestore

        val pref = getSharedPreferences("settings", MODE_PRIVATE)
        companyId = pref.getString("id", null)!!

        db.collection("companies").document(companyId).get().addOnSuccessListener {
            val company = it.toObject(Company::class.java)!!
            binding.ratingValueTv.text =
                "${company.greatRating} Great, ${company.okRating} ok, ${company.badRating} bad ratings"
        }

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

        binding.badTv.setOnClickListener {
            db.collection("companies").document(companyId).get().addOnSuccessListener {
                db.collection("companies").document(companyId)
                    .update("badRating", it.getLong("badRating")!! + 1L).addOnSuccessListener {
                        Toast.makeText(this, "Thanks for rating!", Toast.LENGTH_SHORT).show()
                    }
            }
        }

        binding.okTv.setOnClickListener {
            db.collection("companies").document(companyId).get().addOnSuccessListener {
                db.collection("companies").document(companyId)
                    .update("okRating", it.getLong("okRating")!! + 1L).addOnSuccessListener {
                        Toast.makeText(this, "Thanks for rating!", Toast.LENGTH_SHORT).show()
                    }
            }
        }

        binding.greatTv.setOnClickListener {
            db.collection("companies").document(companyId).get().addOnSuccessListener {
                db.collection("companies").document(companyId)
                    .update("greatRating", it.getLong("greatRating")!! + 1L).addOnSuccessListener {
                        Toast.makeText(this, "Thanks for rating!", Toast.LENGTH_SHORT).show()
                    }
            }
        }

    }

}