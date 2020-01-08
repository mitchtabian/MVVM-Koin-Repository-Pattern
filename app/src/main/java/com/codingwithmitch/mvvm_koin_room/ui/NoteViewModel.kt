package com.codingwithmitch.mvvm_koin_room.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingwithmitch.mvp_room_koin.models.Note
import com.codingwithmitch.mvvm_koin_room.repositories.NoteRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

const val EDIT_MODE_ENABLED = 1
const val EDIT_MODE_DISABLED = 2

class NoteViewModel(
    private val noteRepository: NoteRepository
) : INoteViewModel, ViewModel(){

    private val TAG: String = "AppDebug"

    private val _allNotes: MutableLiveData<List<Note>> = MutableLiveData()
    private val _singleNote: MutableLiveData<Note> = MutableLiveData()
    private val _executeSave: MutableLiveData<Boolean> = MutableLiveData()
    private val _mode: MutableLiveData<Int> = MutableLiveData()

    val allNotes: LiveData<List<Note>> get() = _allNotes
    val singleNote: LiveData<Note> get() = _singleNote
    val executeSave: LiveData<Boolean> get() = _executeSave
    val mode: LiveData<Int> get() = _mode

    init {
        setMode(EDIT_MODE_DISABLED)
    }

    override fun insertNote(note: Note) {
        viewModelScope.launch {
            noteRepository.insertNote(note)
        }
    }

    override fun updateNote(note: Note) {
        viewModelScope.launch {
            noteRepository.updateNote(note)
        }
    }

    override fun deleteNote(note: Note) {
        viewModelScope.launch {
            noteRepository.deleteNote(note)
        }
    }

    override fun deleteAllNotes() {
        viewModelScope.launch {
            noteRepository.deleteAllNotes()
        }
    }

    override fun retrieveAllNotes() {
        viewModelScope.launch {
            _allNotes.value = noteRepository.getAllNotes()
        }
    }

    override fun selectNote(note: Note) {
        _singleNote.value = note
    }

    fun setSingleNote(note: Note){
        _singleNote.value = note
    }

    fun setExecuteSave(shouldExecuteNow: Boolean){
        _executeSave.value = shouldExecuteNow
    }

    fun setMode(mode: Int){
        _mode.value = mode
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}









