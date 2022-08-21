package com.example.aisle.ui.home.notes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.aisle.R
import com.example.aisle.ui.home.HomeActivity
import com.example.aisle.ui.login.phonenumber.PhoneViewModel

class NotesFragment: Fragment() {

    private val TAG = "NotesFragment"
    private lateinit var viewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[NotesViewModel::class.java]
        initObservers()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_notes, container, false)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getNotesInfo((requireActivity() as HomeActivity).token)
    }

    private fun initObservers() {
        viewModel.noteInfoScreenListLivedata.observe(this, Observer {
            Log.i(TAG, "initObservers: ${it.size}")
        })
    }

}