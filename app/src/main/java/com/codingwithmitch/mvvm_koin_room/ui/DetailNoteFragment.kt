package com.codingwithmitch.mvvm_koin_room.ui


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.codingwithmitch.mvp_room_koin.models.Note

import com.codingwithmitch.mvvm_koin_room.R
import kotlinx.android.synthetic.main.fragment_detail_note.*
import kotlinx.android.synthetic.main.layout_note_toolbar.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

const val MODE_BUNDLE_KEY = "com.codingwithmitch.mvvm_koin_room.ui.mode"
/**
 * class for viewing and updating notes
 */
class DetailNoteFragment : Fragment(),
    View.OnClickListener,
    View.OnTouchListener,
    GestureDetector.OnGestureListener,
    GestureDetector.OnDoubleTapListener
{

    private val TAG: String = "AppDebug"

    // "SharedViewModel": reuse existing viewmodel from hosting activity
    val viewModel: NoteViewModel by sharedViewModel()

    lateinit var iMainActivity: IMainActivity

    private lateinit var note: Note

    private val gestureDetector: GestureDetector by lazy {
        GestureDetector(this@DetailNoteFragment.context, this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        subscribeObservers()
    }

    private fun setListeners(){
        detail_note_content.setOnTouchListener(this)
        note_toolbar_back_arrow.setOnClickListener(this)
        note_toolbar_check.setOnClickListener(this)
        detail_note_title.setOnClickListener(this)
        detail_note_edit_title.setOnClickListener(this)
        note_toolbar_back_arrow.setOnClickListener {
            iMainActivity.hideSoftKeyboard()
            iMainActivity.onBackPressed()
        }
    }

    private fun subscribeObservers(){
        viewModel.singleNote.observe(viewLifecycleOwner, Observer {
            note = it
            setNotePropertiesToView()
        })

        viewModel.executeSave.observe(viewLifecycleOwner, Observer { executeSave ->
            executeSave?.let{
                if(it){
                    Log.d(TAG, "EXECUTING SAVE: ")
                    updateNote()
                    viewModel.setExecuteSave(false) // reset
                    viewModel.setMode(EDIT_MODE_DISABLED) // reset)
                }
            }
        })

        viewModel.mode.observe(viewLifecycleOwner, Observer {  mode ->
            when(mode){

                EDIT_MODE_DISABLED -> {
                    disableEditMode()
                }

                EDIT_MODE_ENABLED -> {
                    enableEditMode()
                }
            }
        })
    }

    fun setNotePropertiesToView(){
        detail_note_edit_title.setText(note.title)
        detail_note_title.text = note.title
        detail_note_content.setText(note.content) // setText() b/c custom view? Not sure but don't care.
    }

    fun setNoteProperties(){
        note.updated = System.currentTimeMillis()
        note.title = detail_note_edit_title.text.toString()
        note.content = detail_note_content.text.toString()
    }

    fun updateNote() {
        setNoteProperties()
        if(validateFields()){
            viewModel.updateNote(note)
        }
    }

    private fun validateFields(): Boolean {
        when{
            note.title.isBlank() -> {
                return false
            }
        }
        return true
    }

    override fun onPause() {
        super.onPause()
        // for restoring after process death or config change
        setNoteProperties()
        viewModel.setSingleNote(note)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try{
            iMainActivity = context as IMainActivity
        }catch(e: ClassCastException){
            Log.e(TAG, "$context must implement IMainActivity" )
        }
    }

    override fun onClick(view: View?) {
        when(view?.id){

            R.id.note_toolbar_back_arrow -> {
                iMainActivity.hideSoftKeyboard()
                iMainActivity.onBackPressed()
            }

            R.id.note_toolbar_check -> {
                iMainActivity.hideSoftKeyboard()
                iMainActivity.onBackPressed()
            }

            R.id.detail_note_title ->{
                viewModel.setMode(EDIT_MODE_ENABLED)
                detail_note_edit_title.requestFocus()
                detail_note_edit_title.setSelection(detail_note_edit_title.length())
            }
        }
    }

    override fun onTouch(view: View?, motionEvent: MotionEvent?): Boolean {
        return gestureDetector.onTouchEvent(motionEvent)
    }

    override fun onShowPress(p0: MotionEvent?) {
    }

    override fun onSingleTapUp(p0: MotionEvent?): Boolean {
        return false
    }

    override fun onDown(p0: MotionEvent?): Boolean {
        return false
    }

    override fun onFling(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
        return false
    }

    override fun onScroll(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
        return false
    }

    override fun onLongPress(p0: MotionEvent?) {

    }

    override fun onDoubleTap(p0: MotionEvent?): Boolean {
        viewModel.setMode(EDIT_MODE_ENABLED)
        return false
    }

    override fun onDoubleTapEvent(p0: MotionEvent?): Boolean {
        return false
    }

    override fun onSingleTapConfirmed(p0: MotionEvent?): Boolean {
        return false
    }


}





