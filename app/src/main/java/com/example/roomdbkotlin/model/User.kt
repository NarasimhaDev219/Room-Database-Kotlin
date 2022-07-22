package com.example.roomdbkotlin.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "user_table")
class User(

    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val firstName: String,
    val lastname: String,
    val age : Int
): Parcelable