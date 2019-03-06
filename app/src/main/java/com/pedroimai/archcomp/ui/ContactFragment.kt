package com.pedroimai.archcomp.ui

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pedroimai.archcomp.R
import com.pedroimai.archcomp.model.Contact
import com.pedroimai.archcomp.viewmodel.SharedContactViewModel
import kotlinx.android.synthetic.main.contact_fragment.*

class ContactFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.contact_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val factory =
            SharedContactViewModel.Factory((activity as FragmentActivity).application)

        val viewModel = ViewModelProviders.of(activity as FragmentActivity, factory)
            .get(SharedContactViewModel::class.java)
        subscribeToModel(viewModel)
    }


    private fun subscribeToModel(viewModel: SharedContactViewModel) {
        viewModel.selectedContact.observe(
            this,
            Observer<Contact> { contact ->
                contact?.let {
                    photo.text = contact.photo
                    name.text = contact.name
                    email.text = contact.email
                    phone.text = contact.phone
                }

            }
        )

    }

}
