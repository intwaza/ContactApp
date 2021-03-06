package com.example.contactapp.models

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Contacts")
data class Contact(
    @PrimaryKey(autoGenerate = true) @NonNull var contactId: Int,
    var name: String,
    var phoneNumber: String,
    var email: String,
    var imgUrl: String
)
