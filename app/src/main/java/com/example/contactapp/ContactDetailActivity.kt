package com.example.contactapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class ContactDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)


        val tvContactName = findViewById<TextView>(R.id.tvContactName)
        val tvContactEmail = findViewById<TextView>(R.id.tvContactEmail)
        val tvContactPhoneNumber =findViewById<TextView>(R.id.tvContactPhoneNumber)
        val contactImage = findViewById<ImageView>(R.id.imgContact)


        var nameIntent = intent.getStringExtra(("NAME"))
        var emailIntent = intent.getStringExtra("Email")
        var phoneNumber = intent.getStringExtra("phoneNumber")
        var imageUrl = intent.getStringExtra("imageUrl")
            Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.avatar)
                .into(contactImage)



        tvContactName.text = nameIntent
        tvContactEmail.text  = emailIntent
        tvContactPhoneNumber.text = phoneNumber

    }
}