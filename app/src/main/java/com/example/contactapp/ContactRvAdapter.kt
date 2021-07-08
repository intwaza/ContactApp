package com.example.contactapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class ContactRvAdapter (var contactList: List<Contact>): RecyclerView.Adapter<ContactViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        var itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.contact_list_item,parent,false)
        return ContactViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        var currentContact = contactList.get(position)
        holder.tvName.text = currentContact.name
        holder.tvPhoneNumber.text = currentContact.phoneNumber
        holder.tvEmail.text = currentContact.email
        holder.tvTime.text = currentContact.Time
        Picasso.get().load(currentContact.imgUrl).placeholder(R.drawable.avatar).into(holder.imgUrl)

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
    var imgUrl = itemView.findViewById<ImageView>(R.id.imageView)
}