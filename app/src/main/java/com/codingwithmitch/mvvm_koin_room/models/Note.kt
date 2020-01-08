package com.codingwithmitch.mvp_room_koin.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

const val SINGLE_NOTE_BUNDLE_KEY = "com.codingwithmitch.mvp_room_koin.models.create_note"

@Parcelize
@Entity(tableName = "notes")
class Note(

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "content")
    var content: String? = "",

    @ColumnInfo(name = "updated")
    var updated: Long = System.currentTimeMillis() // default is current time
) : Parcelable{

    override fun toString(): String {
        return "Note(id=$id, title='$title', content=$content, updated=$updated)"
    }
}
