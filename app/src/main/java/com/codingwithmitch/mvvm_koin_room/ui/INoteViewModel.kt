package com.codingwithmitch.mvvm_koin_room.ui

import com.codingwithmitch.mvp_room_koin.models.Note

interface INoteViewModel {

    // NoteListFragment
    fun insertNote(note: Note)

    fun updateNote(note: Note)

    fun deleteNote(note: Note)

    fun deleteAllNotes()

    fun retrieveAllNotes()

    fun selectNote(note: Note)

    // NoteDetailFragment

}