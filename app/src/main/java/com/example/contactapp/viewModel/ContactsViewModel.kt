package com.example.contactapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactapp.models.Contact
import com.example.contactapp.repository.ContactsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor( val contactsRepository: ContactsRepository) : ViewModel() {
    lateinit var contactsLiveData: LiveData<List<Contact>>
    lateinit var contactLiveData: LiveData<Contact>

    fun saveContact(contact: Contact){
        viewModelScope.launch {
            contactsRepository.saveContact(contact)
        }
    }
    fun getAllContacts(){
        contactsLiveData= contactsRepository.fetchContact()
    }

    fun getContactById(contactId: Int){
        contactLiveData= contactsRepository.getContactById(contactId)
    }

    fun deleteContact(contact: Contact){
        viewModelScope.launch {
            contactsRepository.deleteContact(contact)
        }
    }
}