package com.pedroimai.archcomp

import androidx.lifecycle.LiveData
import com.pedroimai.archcomp.model.Contact
import com.pedroimai.archcomp.model.SimpleContact

class ContactRepository(private val api: ContactApi) {
    companion object {
        @Volatile
        private var INSTANCE: ContactRepository? = null

        fun getInstance(api: ContactApi): ContactRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: ContactRepository(api).also { INSTANCE = it }
            }
    }

    fun getContactById(id: String): LiveData<Contact> = api.getContactById(id)

    val contacts: LiveData<List<SimpleContact>>
        get() = api.getContacts()

}
