package com.uma.upsoundrecorder.repository.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Umapathi on 05/02/19.
 * Copyright Indyzen Inc, @2019
 */
@Entity
class AudioDataModel(
    @field:ColumnInfo(name = "title") var title: String,
    @field:ColumnInfo(name = "duration") var duration: String,
    @field:ColumnInfo(name = "time") var time: String,
    @field:ColumnInfo(name = "path") var path: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}