package com.example.contactapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ContactActivity : AppCompatActivity() {
    lateinit var rvContacts: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)
        displayContact()
    }
    fun displayContact(){
        var contactList = listOf<Contact>(
            Contact("Intwaza Belyse","+25071877792","iintwazabelyse@gmail.com","5min ago","https://images.unsplash.com/photo-1604426633861-11b2faead63c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1000&q=80"),
            Contact("Nsegiyumva Liliane","+207816282923","nsediyumvaliliane@gmail.com","15min ago","https://www.incimages.com/uploaded_files/image/1920x1080/getty_624206636_200013332000928034_376810.jpg"),
            Contact("Shadya Obuya","078287839","shadyaobuya@gmail.com","3hours ago","https://images.unsplash.com/photo-1558507652-2d9626c4e67a?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1000&q=80"),
            Contact("Ishimwe Denyse","07932800","ishimwedenyse@gmail.com","2days ago","https://images.unsplash.com/photo-1579591919791-0e3737ae3808?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1000&q=80"),
            Contact("Hamdi Abas","07569292","abashamid@gmail.com","4days ago","https://i.pinimg.com/736x/de/25/db/de25db4cfd173311a76ed7e163a3a437.jpg"),
            Contact("Rahamu James","07829302","rahamujames@gmail.com","7days ago","https://cdn.crello.com/api/media/medium/166328876/stock-photo-young-muslim-man"),
            Contact("Munyiva Audrey","0782822992","munyivaaudrey@gmail.com","5month ago","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTVuPwvBiCvV2qQK1GyyhWI15B5p3p0xsGB1w&usqp=CAU")
        )
        rvContacts= findViewById(R.id.rvContacts)
        var contactAdapter = ContactRvAdapter(contactList,baseContext)
        rvContacts.layoutManager= LinearLayoutManager(baseContext)
        rvContacts.adapter= contactAdapter
    }
}