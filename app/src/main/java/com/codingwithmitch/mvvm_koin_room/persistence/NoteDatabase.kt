package com.codingwithmitch.mvvm_koin_room.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codingwithmitch.mvp_room_koin.models.Note

const val NOTE_DATABASE_NAME = "notes_database"

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase: RoomDatabase(){

    abstract fun noteDao(): NoteDao
}