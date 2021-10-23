package com.example.contactapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.contactapp.models.Contact
import com.example.contactapp.ui.ContactActivity
import com.example.contactapp.ui.ContactDetailActivity
import com.squareup.picasso.Picasso


class ContactRvAdapter (var contactList: List<Contact>, var context:Context, var contactClickListener: ContactActivity.ContactClickListener): RecyclerView.Adapter<ContactViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        var itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.contact_list_item,parent,false)
        return ContactViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        var currentContact = contactList.get(position)
        holder.tvName.text = currentContact.contactId.toString()
        holder.tvPhoneNumber.text = currentContact.phoneNumber
        holder.tvEmail.text = currentContact.email
        holder.tvTime.text = currentContact.Time
        if(currentContact.imgUrl.isNotEmpty()){
            Picasso.get()
                .load(currentContact.imgUrl)
                .resize(80,80)
                .centerCrop()
                .placeholder(R.drawable.avatar)
                .into(holder.imageContact)
        }


        holder.cvContact.setOnClickListener {
            var intent = Intent(context, ContactDetailActivity::class.java)
            intent.putExtra("NAME", currentContact.contactId)
            intent.putExtra("Email",currentContact.email)
            intent.putExtra("phoneNumber",currentContact.phoneNumber)
            intent.putExtra("imageUrl",currentContact.imgUrl)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent)
        }
        holder.imageContact.setOnClickListener {
            Toast.makeText(context,"you have clicked on my face", Toast.LENGTH_LONG).show()
        }
        holder.ivDeleteContact.setOnClickListener {
            contactClickListener.onclickDeleteContact(currentContact)
        }
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

}
class ContactViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
    var tvName = itemView.findViewById<TextView>(R.id.tvName)
    var tvPhoneNumber = itemView.findViewById<TextView>(R.id.tvPhoneNumber)
    var tvEmail = itemView.findViewById<TextView>(R.id.tvEmail)
    var tvTime = itemView.findViewById<TextView>(R.id.tvTime)
    var imageContact = itemView.findViewById<ImageView>(R.id.imageView)
    var cvContact = itemView.findViewById<CardView>(R.id.cvContact)
    var ivDeleteContact= itemView.findViewById<ImageView>(R.id.IvDeleteContact)

}