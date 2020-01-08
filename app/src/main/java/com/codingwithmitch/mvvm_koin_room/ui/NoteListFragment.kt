package com.codingwithmitch.mvvm_koin_room.ui


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.codingwithmitch.mvp_room_koin.models.Note

import com.codingwithmitch.mvvm_koin_room.R
import com.codingwithmitch.mvvm_koin_room.util.TopSpacingItemDecoration
import kotlinx.android.synthetic.main.fragment_note_list.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

/**
 * View a list of all notes
 */
class NoteListFragment : Fragment(),
    NoteListAdapter.Interaction
{

    private val TAG: String = "AppDebug"

    // "SharedViewModel": reuse existing viewmodel from hosting activity
    val viewModel: NoteViewModel by sharedViewModel()

    lateinit var listAdapter: NoteListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_note_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        add_note_fab.setOnClickListener {
            findNavController().navigate(R.id.action_noteListFragment_to_createNoteFragment)
        }

        subscribeObservers()

        initRecyclerView()
        getAllNotes()
    }

    private fun initRecyclerView() {
        recyclerview.apply {
            listAdapter = NoteListAdapter(this@NoteListFragment)
            val layoutManager = LinearLayoutManager(this@NoteListFragment.context)
            recyclerview.addItemDecoration(TopSpacingItemDecoration(15))
            recyclerview.layoutManager = layoutManager
            recyclerview.adapter = listAdapter
        }
    }

    private fun subscribeObservers(){
        viewModel.allNotes.observe(viewLifecycleOwner, Observer { notesList ->
            notesList?.let{
                for(note in notesList){
                    Log.d(TAG, "$note")
                }
                listAdapter.submitList(notesList)
            }
        })
    }

    private fun getAllNotes(){
        viewModel.retrieveAllNotes()
    }

    override fun onItemSelected(position: Int, item: Note) {
        viewModel.setSingleNote(item)
        findNavController().navigate(R.id.action_noteListFragment_to_detailNoteFragment)
    }
}














