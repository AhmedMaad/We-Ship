package com.maad.weship.general

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.maad.weship.R
import com.maad.weship.databinding.ActivityProfileBinding
import java.io.File

class ProfileActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var binding: ActivityProfileBinding
    private var imageUri: Uri? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = Firebase.firestore

        val pref = getSharedPreferences("settings", MODE_PRIVATE)
        val companyId = pref.getString("id", null)!!
        db.collection("companies").document(companyId).get().addOnSuccessListener { c ->
            val company = c.toObject(Company::class.java)!!
            db.collection("representatives").document(companyId).get().addOnSuccessListener { r ->
                val representative = r.toObject(Representative::class.java)!!
                binding.progress.visibility = View.INVISIBLE
                binding.companyNameTv.text = company.companyName
                binding.companyEmailTv.text = company.email
                binding.companyTypeTv.text = company.companyType
                binding.commercialRegistrationTv.text = company.commercialRegistration
                binding.taxNumberTv.text = company.taxNumber
                binding.representativeIdTv.text = representative.nationalId
                if (company.image.isEmpty())
                    binding.profileIv.setImageResource(R.drawable.ic_person)
                else
                    Glide.with(this).load(company.image).into(binding.profileIv)
            }

        }

        binding.profileIv.setOnClickListener {
            val i = Intent(Intent.ACTION_GET_CONTENT)
            i.type = "image/*"
            startActivityForResult(i, 50)
        }

        binding.updateBtn.setOnClickListener {
            //TODO: Upload image
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            imageUri = data?.data
            binding.profileIv.setImageURI(imageUri)
        }

    }

}