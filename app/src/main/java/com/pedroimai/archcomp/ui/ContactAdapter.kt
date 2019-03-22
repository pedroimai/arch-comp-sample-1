package com.pedroimai.archcomp.ui

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pedroimai.archcomp.R
import com.pedroimai.archcomp.model.Contact
import com.pedroimai.archcomp.model.SimpleContact
import kotlinx.android.synthetic.main.contact_item.view.*
import java.util.*

class ContactAdapter(private val itemClick: (SimpleContact) -> Unit) :
    RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    private var contactList: ArrayList<SimpleContact> = ArrayList()

    fun swap(newContacts: List<SimpleContact>) {
        contactList.clear()
        contactList.addAll(newContacts)
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ContactViewHolder(layoutInflater.inflate(R.layout.contact_item, parent,false ), itemClick)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bindContact(contactList[position])
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    class ContactViewHolder(view: View, private val itemClick: (SimpleContact) -> Unit) :
        RecyclerView.ViewHolder(view) {

        fun bindContact(contact: SimpleContact) {
            itemView.name.text = contact.name
           // itemView.emoji_picture.text = contact.photo
            itemView.setOnClickListener { itemClick(contact) }
        }
    }
}
