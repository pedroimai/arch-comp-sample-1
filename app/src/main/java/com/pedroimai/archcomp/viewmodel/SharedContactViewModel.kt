package com.pedroimai.archcomp.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.pedroimai.archcomp.ArchCompApp
import com.pedroimai.archcomp.ContactRepository
import com.pedroimai.archcomp.model.Contact
import com.pedroimai.archcomp.model.SimpleContact


class SharedContactViewModel(repository: ContactRepository) : ViewModel() {
    private val selectedContactId = MutableLiveData<String>()

    val selectedContact: LiveData<Contact> =
        Transformations.switchMap(selectedContactId) { id ->
            if (id.isValid) repository.getContactById(id)
            else MutableLiveData<Contact>().apply { value = null }
        }

    infix fun select(contact: SimpleContact) {
        selectedContactId.value = contact.id
    }

    val observableContactList: LiveData<List<SimpleContact>> = repository.contacts

    class Factory(application: Application) : ViewModelProvider.NewInstanceFactory() {

        private val repository: ContactRepository =
            (application as ArchCompApp).getRepository()

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SharedContactViewModel(repository) as T
        }
    }
}

private val String.isValid: Boolean
    get() {
        return this != "0"
    }
