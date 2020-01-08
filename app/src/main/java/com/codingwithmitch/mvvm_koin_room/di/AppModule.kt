package com.codingwithmitch.mvvm_koin_room.di

import androidx.room.Room
import com.codingwithmitch.mvvm_koin_room.persistence.NOTE_DATABASE_NAME
import com.codingwithmitch.mvvm_koin_room.persistence.NoteDatabase
import com.codingwithmitch.mvvm_koin_room.repositories.NoteRepository
import com.codingwithmitch.mvvm_koin_room.repositories.NoteRepositoryImpl
import com.codingwithmitch.mvvm_koin_room.ui.NoteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
 *  See https://start.insert-koin.io/#/quickstart/android
 **/
val appModule = module{

    // Provide NoteDatabase
    single{ Room.databaseBuilder(get(), NoteDatabase::class.java, NOTE_DATABASE_NAME).build() }

    // Provide NoteDao
    single{ get<NoteDatabase>().noteDao() }

    // Provide NoteRepository
    single<NoteRepository> { NoteRepositoryImpl(get()) }

    // Provide NoteViewModel
    viewModel { NoteViewModel(get()) }
}