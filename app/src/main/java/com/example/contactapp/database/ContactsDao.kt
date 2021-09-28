package com.example.contactapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.contactapp.models.Contact

@Dao
interface ContactsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContact(contact: Contact)

    @Query("SELECT * FROM Contacts")
    fun getAllContacts(): LiveData<List<Contact>>

    @Query("SELECT * FROM Contacts WHERE contactId = :contactId")
    fun getContactById(contactId: Int): LiveData<Contact>

    @Delete
    fun  deleteContact(contact: Contact)
}