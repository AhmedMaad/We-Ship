package com.maad.weship.general

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import com.maad.weship.company.Company
import com.maad.weship.company.CompanyHomeActivity
import com.maad.weship.databinding.ActivitySignInBinding
import com.maad.weship.shipping.ShippingCompany
import com.maad.weship.shipping.ShippingCompanyHomeActivity

class SignInActivity : ParentActivity() {

    private lateinit var db: FirebaseFirestore
    private var companyFlag = false
    private var shippingFlag = false
    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = Firebase.firestore

        binding.loginBtn.setOnClickListener {
            val username = binding.usernameEt.text.toString()
            val password = binding.passwordEt.text.toString()
            if (username.isEmpty() || password.isEmpty()) {
                binding.usernameEt.error = "Required"
                binding.passwordEt.error = "Required"
            } else {
                binding.progress.visibility = View.VISIBLE
                binding.loginBtn.visibility = View.INVISIBLE
                db.collection("companies").get().addOnSuccessListener {
                    val allCompanies = it.toObjects<Company>()
                    for (company in allCompanies)
                        if (company.username == username) {
                            companyFlag = true
                            makeLogin(company.email, password)
                            break
                        }

                    if (!companyFlag)
                        db.collection("shippingCompanies").get().addOnSuccessListener { x ->
                            val allShippingCompanies = x.toObjects(ShippingCompany::class.java)
                            for (shipping in allShippingCompanies)
                                if (shipping.username == username) {
                                    shippingFlag = true
                                    makeLogin(shipping.email, password)
                                    break
                                }

                            if (!companyFlag && !shippingFlag) {
                                Toast.makeText(
                                    this,
                                    "Wrong username or password",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                                binding.progress.visibility = View.INVISIBLE
                                binding.loginBtn.visibility = View.VISIBLE
                            }

                        }

                }
            }

        }




        binding.forgotPasswordTv.setOnClickListener {
            val username = binding.usernameEt.text.toString()
            if (username.isEmpty())
                Toast.makeText(this, "Username is required", Toast.LENGTH_SHORT).show();
            else {
                binding.progress.visibility = View.VISIBLE
                binding.loginBtn.visibility = View.INVISIBLE
                db.collection("companies").get().addOnSuccessListener {
                    val allCompanies = it.toObjects<Company>()
                    for (company in allCompanies)
                        if (company.username == username) {
                            companyFlag = true
                            resetPassword(company.email)
                            break
                        }

                    if (!companyFlag)
                        db.collection("shippingCompanies").get().addOnSuccessListener { x ->
                            val allShippingCompanies = x.toObjects(ShippingCompany::class.java)
                            for (shipping in allShippingCompanies)
                                if (shipping.username == username) {
                                    shippingFlag = true
                                    resetPassword(shipping.email)
                                    break
                                }

                            if (!companyFlag && !shippingFlag) {
                                Toast.makeText(
                                    this,
                                    "Wrong username",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                                binding.progress.visibility = View.INVISIBLE
                                binding.loginBtn.visibility = View.VISIBLE
                            }

                        }

                }
            }

        }

    }

    private fun resetPassword(email: String) {
        Firebase.auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    binding.progress.visibility = View.INVISIBLE
                    binding.loginBtn.visibility = View.VISIBLE
                    Toast.makeText(this, "Check your email", Toast.LENGTH_SHORT).show();
                }
            }
    }

    private fun makeLogin(email: String, password: String) {
        val auth = Firebase.auth
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val id = task.result.user!!.uid
                    val pref = getSharedPreferences("settings", MODE_PRIVATE).edit()
                    pref.putString("id", id)
                    pref.apply()
                    if (companyFlag)
                        startActivity(Intent(this, CompanyHomeActivity::class.java))
                    else if (shippingFlag)
                        startActivity(Intent(this, ShippingCompanyHomeActivity::class.java))
                    finishAffinity()
                }

            }
    }


}