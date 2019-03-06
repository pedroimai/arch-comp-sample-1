package com.pedroimai.archcomp

import androidx.lifecycle.MutableLiveData
import com.pedroimai.archcomp.model.Contact
import com.pedroimai.archcomp.model.SimpleContact

class ContactApi(private val executors: AppExecutors) {
    companion object {
        @Volatile
        private var INSTANCE: ContactApi? = null

        fun getInstance(executors: AppExecutors): ContactApi =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: ContactApi(executors).also { INSTANCE = it }
            }
    }

    fun getContacts(): MutableLiveData<List<SimpleContact>> {
        val contacts = MutableLiveData<List<SimpleContact>>()
        executors.diskIO().execute {
            Thread.sleep(3000)
            contacts.postValue(fakeSimpleContactList)
        }
        return contacts
    }

    fun getContactById(id: String): MutableLiveData<Contact> {
        val contact = MutableLiveData<Contact>()
        contact.postValue(fakeContactList.find { it.id == id })
        return contact
    }
}

val fakeContactList = listOf(
    Contact(
        id = "1",
        name = "Jose Silva",
        photo = "\ud83d\ude02",
        email = "jose@email.com",
        phone = "(11)91010-1010"
    ),
    Contact(
        id = "2",
        name = "Maria Silva",
        photo = "\uD83D\uDC69\u200D\uD83D\uDCBB",
        email = "maria@email.com",
        phone = "(11)92020-2020"
    ),
    Contact(
        id = "3",
        name = "Valter Silva",
        photo = "\ud83d\ude03",
        email = "valter@email.com",
        phone = "(11)93030-3030"
    ),
    Contact(
        id = "4",
        name = "Pedro Silva",
        photo = "\ud83d\ude04",
        email = "pedro@email.com",
        phone = "(11)94040-4040"
    ),
    Contact(
        id = "5",
        name = "Lucio Silva",
        photo = "\ud83d\ude05",
        email = "lucio@email.com",
        phone = "(11)95050-5050"
    ),
    Contact(
        id = "6",
        name = "Bruno Silva",
        photo = "\ud83d\ude06",
        email = "bruno@email.com",
        phone = "(11)96060-6060"
    )
)

val fakeSimpleContactList = listOf(
    SimpleContact(
        id = "1",
        name = "Jose Silva",
        photo = "\ud83d\ude02"
        ),
    SimpleContact(
        id = "2",
        name = "Maria Silva",
        photo = "\uD83D\uDC69\u200D\uD83D\uDCBB"
        ),
    SimpleContact(
        id = "3",
        name = "Valter Silva",
        photo = "\ud83d\ude03"
        ),
    SimpleContact(
        id = "4",
        name = "Pedro Silva",
        photo = "\ud83d\ude04"
        ),
    SimpleContact(
        id = "5",
        name = "Lucio Silva",
        photo = "\ud83d\ude05"
    ),
    SimpleContact(
        id = "6",
        name = "Bruno Silva",
        photo = "\ud83d\ude06"
    )
)
