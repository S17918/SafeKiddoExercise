package com.safekiddo.exercise.domain.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "posts")
@Parcelize
data class Post(

    @NonNull
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "icon")
    var featuredImage: String,

    @ColumnInfo(name = "favourite")
    var favourite: Boolean
) : Parcelable {
    constructor(title: String, description: String, featuredImage: String, favourite: Boolean) : this(0, title, description, featuredImage, false)
}