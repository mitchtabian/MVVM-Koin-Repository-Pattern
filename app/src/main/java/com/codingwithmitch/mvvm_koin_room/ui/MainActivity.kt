package com.codingwithmitch.mvvm_koin_room.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import com.codingwithmitch.mvp_room_koin.models.Note
import com.codingwithmitch.mvp_room_koin.models.SINGLE_NOTE_BUNDLE_KEY
import com.codingwithmitch.mvvm_koin_room.R
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(),
    IMainActivity
{

    private val TAG: String = "AppDebug"

    // provide viewmodel instance using koin
    val viewModel: NoteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState != null){
            (savedInstanceState[SINGLE_NOTE_BUNDLE_KEY] as Note?)?.let {note ->
                viewModel.setSingleNote(note)
            }
            (savedInstanceState[MODE_BUNDLE_KEY] as Int?)?.let { mode ->
                viewModel.setMode(mode)
            }
        }
    }

    override fun onBackPressed() {
        viewModel.setExecuteSave(true) // save any notes being updated or created
        super.onBackPressed()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(SINGLE_NOTE_BUNDLE_KEY, viewModel.singleNote.value)
        viewModel.mode.value?.let { mode ->
            outState.putInt(MODE_BUNDLE_KEY, mode)
        }

    }

    override fun hideSoftKeyboard() {
        if (currentFocus != null) {
            val inputMethodManager = getSystemService(
                Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager
                .hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
    }
}






