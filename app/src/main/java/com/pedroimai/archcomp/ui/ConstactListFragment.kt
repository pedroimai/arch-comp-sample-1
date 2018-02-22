package com.pedroimai.archcomp.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pedroimai.archcomp.R
import com.pedroimai.archcomp.model.SimpleContact
import com.pedroimai.archcomp.viewmodel.SharedContactViewModel
import kotlinx.android.synthetic.main.contact_list_fragment.*

val contactsPlaceholder = listOf(
    SimpleContact(id = "0", name = "Nome", photo = ":D"),
    SimpleContact(id = "0", name = "Nome", photo = ":D"),
    SimpleContact(id = "0", name = "Nome", photo = ":D")
)

class ContactListFragment : Fragment() {

    private lateinit var adapter: ContactAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.contact_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val factory =
            SharedContactViewModel.Factory((activity as FragmentActivity).application)

        val viewModel = ViewModelProviders.of(activity as FragmentActivity, factory)
            .get(SharedContactViewModel::class.java)

        initRecyclerView(viewModel)

        subscribeUi(viewModel)
    }

    private fun initRecyclerView(viewModel: SharedContactViewModel) {
        val layoutManager =
            LinearLayoutManager(activity?.baseContext, LinearLayoutManager.HORIZONTAL, false)

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(contact_list)

        adapter = ContactAdapter { viewModel select it }
        contact_list.layoutManager = layoutManager
        contact_list.adapter = adapter
        adapter.swap(contactsPlaceholder)


    }

    private fun subscribeUi(viewModelShared: SharedContactViewModel) {
        viewModelShared.observableContactList.observe(this,
            Observer<List<SimpleContact>> { contacts ->
                contacts?.let { adapter.swap(contacts) }
            })
    }

}