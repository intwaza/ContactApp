package com.example.contactapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.contactapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var btnContacts: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        castView()
        click()
    }
    fun castView(){
        btnContacts= findViewById(R.id.btnContacts)
    }
    fun click(){
        btnContacts.setOnClickListener {
            var intent = Intent(baseContext, ContactActivity::class.java)
            startActivity(intent)
        }
    }

}