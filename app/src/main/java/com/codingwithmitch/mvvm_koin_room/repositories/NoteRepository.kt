package com.codingwithmitch.mvvm_koin_room.repositories

import com.codingwithmitch.mvp_room_koin.models.Note

interface NoteRepository {

    suspend fun insertNote(note: Note)

    suspend fun updateNote(note: Note)

    suspend fun deleteNote(note: Note)

    suspend fun deleteAllNotes()

    suspend fun getAllNotes(): List<Note>
}