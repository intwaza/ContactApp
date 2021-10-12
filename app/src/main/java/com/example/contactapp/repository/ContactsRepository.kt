package com.example.contactapp.repository

import androidx.lifecycle.LiveData
import com.example.contactapp.ContactsApp
import com.example.contactapp.database.ContactsDao
import com.example.contactapp.database.ContactsDatabase
import com.example.contactapp.models.Contact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ContactsRepository @Inject constructor(val  contactsDao: ContactsDao){
//    val database= ContactsDatabase.getDatabase(ContactsApp.appContext)

    suspend fun saveContact(contact: Contact){
        withContext(Dispatchers.IO){
            contactsDao.insertContact(contact)
        }
    }

    fun fetchContact(): LiveData<List<Contact>>{
        return  contactsDao.getAllContacts()
    }
    fun getContactById(contactId: Int):LiveData<Contact>{
        return contactsDao.getContactById(contactId)

    }
    suspend fun deleteContact(contact: Contact){
        withContext(Dispatchers.IO){
           contactsDao.deleteContact(contact)
        }
    }
}
