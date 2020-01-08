package com.codingwithmitch.mvvm_koin_room.ui

import android.view.View
import android.widget.EditText
import kotlinx.android.synthetic.main.fragment_detail_note.*
import kotlinx.android.synthetic.main.layout_note_toolbar.*

fun DetailNoteFragment.disableContentInteraction(){
    detail_note_content.setKeyListener(null)
    detail_note_content.setFocusable(false)
    detail_note_content.setFocusableInTouchMode(false)
    detail_note_content.setCursorVisible(false)
    detail_note_content.clearFocus()
}


fun DetailNoteFragment.enableContentInteraction(){
    detail_note_content.setKeyListener(EditText(this.context).keyListener)
    detail_note_content.setFocusable(true)
    detail_note_content.setFocusableInTouchMode(true)
    detail_note_content.setCursorVisible(true)
    detail_note_content.requestFocus()
}


fun DetailNoteFragment.enableEditMode() {
    back_arrow_container.setVisibility(View.GONE)
    check_container.setVisibility(View.VISIBLE)
    detail_note_title.setVisibility(View.GONE)
    detail_note_edit_title.setVisibility(View.VISIBLE)
    enableContentInteraction()
}

fun DetailNoteFragment.disableEditMode(){
    back_arrow_container.setVisibility(View.VISIBLE)
    check_container.setVisibility(View.GONE)
    detail_note_title.setVisibility(View.VISIBLE)
    detail_note_edit_title.setVisibility(View.GONE)
    disableContentInteraction()
}















