package com.example.contactapp.models

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Contacts")
data class Contact(
    @PrimaryKey(autoGenerate = true) @NonNull var contactId: Int,
    var phoneNumber: String,
    var email: String,
    var Time: String,
    var imgUrl: String
)
