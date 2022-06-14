package com.maad.weship.general

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import com.maad.weship.company.CompanyHomeActivity
import com.maad.weship.databinding.ActivitySignInBinding
import com.maad.weship.shipping.ShippingCompanyHomeActivity

class SignInActivity : ParentActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var binding: ActivitySignInBinding
    private var flag = false
    private var resetFlag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = Firebase.firestore

        binding.loginBtn.setOnClickListener {
            val username = binding.usernameEt.text.toString()
            //val password = binding.passwordEt.text.toString()
            //val username = "c1"
            val password = "123456"
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
                            flag = true
                            makeLogin(
                                company.email,
                                password,
                                company.userType,
                                company.companyName
                            )
                            break
                        }
                    if (!flag) {
                        Toast.makeText(this, "Wrong username", Toast.LENGTH_SHORT).show()
                        binding.progress.visibility = View.INVISIBLE
                        binding.loginBtn.visibility = View.VISIBLE
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
                            resetFlag = true
                            resetPassword(company.email)
                            break
                        }

                    if (!resetFlag) {
                        Toast.makeText(this, "Wrong username", Toast.LENGTH_SHORT).show()
                        binding.progress.visibility = View.INVISIBLE
                        binding.loginBtn.visibility = View.VISIBLE
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
                    Toast.makeText(this, "Check your email", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun makeLogin(email: String, password: String, userType: String, companyName: String) {
        val auth = Firebase.auth
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val id = task.result.user!!.uid
                    val pref = getSharedPreferences("settings", MODE_PRIVATE).edit()
                    pref.putString("id", id)
                    pref.apply()
                    when (userType) {
                        "shipping" -> {
                            val i = Intent(this, ShippingCompanyHomeActivity::class.java)
                            i.putExtra("name", companyName)
                            startActivity(i)
                            finishAffinity()
                        }
                        "other" -> {
                            startActivity(Intent(this, CompanyHomeActivity::class.java))
                            finishAffinity()
                        }
                    }
                } else {
                    Toast.makeText(this, "Wrong username", Toast.LENGTH_SHORT).show()
                    binding.progress.visibility = View.INVISIBLE
                    binding.loginBtn.visibility = View.VISIBLE
                }

            }
    }


}