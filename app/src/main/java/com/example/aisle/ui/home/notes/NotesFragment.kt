package com.example.aisle.ui.home.notes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aisle.R
import com.example.aisle.ui.home.HomeActivity
import com.example.aisle.ui.home.notes.models.NotesRv


class NotesFragment: Fragment() {

    private val TAG = "NotesFragment"
    private lateinit var viewModel: NotesViewModel
    private lateinit var rvNotes: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[NotesViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_notes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi(view)
        initObservers()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getNotesInfo((requireActivity() as HomeActivity).token)
    }

    private fun initObservers() {
        viewModel.noteInfoScreenListLivedata.observe(viewLifecycleOwner, Observer {
            Log.i(TAG, "initObservers: ${it.size}")
            initNotesRecyclerView(it)
        })
    }

    private fun initUi(view: View) {
        rvNotes = view.findViewById<RecyclerView>(R.id.notes_rv)
    }

    private fun initNotesRecyclerView(notesList: MutableList<NotesRv>) {
        val adapter = NotesAdapter(notesList)
        val layoutManager = GridLayoutManager(activity, 2)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (adapter.getItemViewType(position)) {
                    NotesRv.SECONDARY_SECTION -> {
                        1
                    }
                    else -> {
                        2
                    }
                }
            }

            override fun getSpanIndex(position: Int, spanCount: Int): Int {
                return when (adapter.getItemViewType(position)) {
                    NotesRv.SECONDARY_SECTION -> {
                        1
                    }
                    else -> {
                        2
                    }
                }
            }
        }
        rvNotes.layoutManager = layoutManager
        rvNotes.adapter = adapter
        (rvNotes.adapter as NotesAdapter).notifyDataSetChanged()
    }

}