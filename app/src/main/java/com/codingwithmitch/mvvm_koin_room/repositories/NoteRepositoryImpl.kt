package com.codingwithmitch.mvvm_koin_room.repositories

import android.util.Log
import com.codingwithmitch.mvp_room_koin.models.Note
import com.codingwithmitch.mvvm_koin_room.persistence.NoteDao
import kotlinx.coroutines.delay

class NoteRepositoryImpl(
    private val noteDao: NoteDao
) : NoteRepository{

    private val TAG: String = "AppDebug"

    override suspend fun insertNote(note: Note) {
        noteDao.insertNote(note)
    }

    override suspend fun updateNote(note: Note) {
        noteDao.updateNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }

    override suspend fun deleteAllNotes() {
        noteDao.deleteAllNotes()
    }

    override suspend fun getAllNotes(): List<Note> {
        return noteDao.getAllNotes()
    }

}


