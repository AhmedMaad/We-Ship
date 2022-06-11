package com.maad.weship.company

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.maad.weship.general.ParentActivity
import com.maad.weship.databinding.ActivityCompanySignUpBinding

class CompanySignUpActivity : ParentActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var binding: ActivityCompanySignUpBinding
    private var id = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCompanySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = Firebase.firestore

        binding.submitBtn.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val username = binding.usernameEt.text.toString()
            val password = binding.passwordEt.text.toString()
            val confirmPassword = binding.confirmPasswordEt.text.toString()
            if (email.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty())
                Toast.makeText(this, "Missing Required Field/s", Toast.LENGTH_SHORT).show()
            else if (password != confirmPassword)
                Toast.makeText(this, "Passwords should match", Toast.LENGTH_SHORT).show();
            else
                addEmailPass(email, password)
        }

    }

    private fun addEmailPass(email: String, password: String) {
        binding.progress.visibility = View.VISIBLE
        binding.submitBtn.visibility = View.INVISIBLE
        val auth = Firebase.auth
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    id = task.result.user!!.uid
                    addCompany()
                } else {
                    Toast.makeText(this, "Try with another email", Toast.LENGTH_SHORT).show()
                    binding.progress.visibility = View.VISIBLE
                }

            }
    }

    private fun addCompany() {
        val email = binding.emailEt.text.toString()
        val username = binding.usernameEt.text.toString()
        val password = binding.passwordEt.text.toString()
        val company = Company(id, email, username, password)
        db
            .collection("companies")
            .document(id)
            .set(company)
            .addOnSuccessListener {
                binding.progress.visibility = View.INVISIBLE
                binding.submitBtn.visibility = View.VISIBLE
                Toast.makeText(this, "Welcome, \"$username\"", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, CompanyHomeActivity::class.java))
                finishAffinity()
            }
    }

}