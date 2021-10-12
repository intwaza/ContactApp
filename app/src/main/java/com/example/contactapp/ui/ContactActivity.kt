package com.example.contactapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contactapp.ContactRvAdapter
import com.example.contactapp.R
import com.example.contactapp.databinding.ActivityContactBinding
import com.example.contactapp.models.Contact
import com.example.contactapp.viewModel.ContactsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactActivity : AppCompatActivity() {
    lateinit var binding: ActivityContactBinding
    val contactsViewModel: ContactsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityContactBinding.inflate(layoutInflater)
        setContentView(binding.root)
        contactsViewModel.getAllContacts()
    }

    override fun onResume() {
        super.onResume()
        contactsViewModel.contactsLiveData.observe(this, {contacts->
            displayContact(contacts)
        })
        binding.floatingActionButton.setOnClickListener{
            startActivity(Intent(this, AddContactActivity::class.java))
        }

    }

    fun displayContact(contactList: List<Contact>){
        var contactAdapter = ContactRvAdapter(contactList,baseContext, object: ContactClickListener{
            override fun onclickDeleteContact(contact: Contact) {
                contactsViewModel.deleteContact(contact)
            }

        })
        binding.rvContacts.layoutManager= LinearLayoutManager(baseContext)
        binding.rvContacts.adapter= contactAdapter
    }

    interface ContactClickListener{
        fun onclickDeleteContact(contact: Contact)
    }
}