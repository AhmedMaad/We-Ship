package com.maad.weship.shipping

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.maad.weship.general.ParentActivity
import com.maad.weship.databinding.ActivityShippingSignUp3Binding

class ShippingSignUp3Activity : ParentActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var binding: ActivityShippingSignUp3Binding
    private var id = ""
    private lateinit var shipping: ShippingCompany

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShippingSignUp3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        db = Firebase.firestore
        shipping = intent.getParcelableExtra("shipping")!!
        binding.submitBtn.setOnClickListener { addEmailPass() }
    }

    private fun addEmailPass() {
        binding.progress.visibility = View.VISIBLE
        binding.submitBtn.visibility = View.INVISIBLE
        val auth = Firebase.auth
        auth.createUserWithEmailAndPassword(shipping.email, shipping.password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    id = task.result.user!!.uid
                    shipping.id = id
                    addShippingCompany()
                } else {
                    Toast.makeText(this, "Try another email", Toast.LENGTH_SHORT).show()
                    binding.progress.visibility = View.INVISIBLE
                    binding.submitBtn.visibility = View.VISIBLE
                }

            }
    }

    private fun addShippingCompany() {

        val representative = Representative(
            binding.representativeFullNameEt.text.toString(),
            binding.nationalIdEt.text.toString(),
            binding.representativeEmailEt.text.toString(),
            binding.phoneNumberEt.text.toString(),
        )

        db.collection("shippingCompanies").document(id).set(shipping).addOnSuccessListener {
            db.collection("representatives").document(id).set(representative).addOnSuccessListener {
                binding.progress.visibility = View.INVISIBLE
                binding.submitBtn.visibility = View.VISIBLE
                Toast.makeText(this, "Welcome, \"${representative.name}\"", Toast.LENGTH_LONG)
                    .show()
                startActivity(Intent(this, ShippingCompanyHomeActivity::class.java))
                finishAffinity()
            }
        }

    }

}