package com.example.contactapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.contactapp.databinding.ActivityAddContactBinding
import com.example.contactapp.models.Contact
import com.example.contactapp.viewModel.ContactsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddContactActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddContactBinding
    val contactsViewModel: ContactsViewModel by  viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddContactBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        binding.btnSaveContact.setOnClickListener {
            validateContact()
        }
    }
    fun validateContact(){
        var name= binding.etName.text.toString()
        var phoneNumber= binding.etPhoneNumber.text.toString()
        var email= binding.etEmail.text.toString()
        var error= false

        if(name. isEmpty() && name.isBlank()){
            error= true
            binding.tilName.error= "name is required"
        }
        if(email. isEmpty() && email.isBlank()){
            error= true
            binding.tilEmail.error= "email is required"
        }
        if(phoneNumber. isEmpty() && phoneNumber.isBlank()){
            error= true
            binding.tilPhoneNumber.error= "phone number is required"
        }
        if (!error){
            var contact= Contact(0, name, phoneNumber, email,"")
            contactsViewModel.saveContact(contact)
            binding.etName.setText("")
            binding.etEmail.setText("")
            binding.etPhoneNumber.setText("")
        }
    }
}