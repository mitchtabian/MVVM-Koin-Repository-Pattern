package com.codingwithmitch.mvvm_koin_room.persistence

import androidx.lifecycle.LiveData
import androidx.room.*
import com.codingwithmitch.mvp_room_koin.models.Note

@Dao
interface NoteDao {

    @Insert
    suspend fun insertNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM notes")
    suspend fun getAllNotes(): List<Note>

    @Query("DELETE FROM notes")
    suspend fun deleteAllNotes()

}