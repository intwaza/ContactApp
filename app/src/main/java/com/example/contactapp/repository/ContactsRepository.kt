package com.example.contactapp.repository

import androidx.lifecycle.LiveData
import com.example.contactapp.ContactsApp
import com.example.contactapp.database.ContactsDatabase
import com.example.contactapp.models.Contact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ContactsRepository {
    val database= ContactsDatabase.getDatabase(ContactsApp.appContext)

    suspend fun saveContact(contact: Contact){
        withContext(Dispatchers.IO){
            database.getContactsDao().insertContact(contact)
        }
    }

    fun fetchContact(): LiveData<List<Contact>>{
        return  database.getContactsDao().getAllContacts()
    }
    fun getContactById(contactId: Int):LiveData<Contact>{
        return database.getContactsDao().getContactById(contactId)

    }
    suspend fun deleteContact(contact: Contact){
        withContext(Dispatchers.IO){
            database.getContactsDao().deleteContact(contact)
        }
    }
}
