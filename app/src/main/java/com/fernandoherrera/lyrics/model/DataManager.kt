package com.fernandoherrera.lyrics.model

import android.content.Context
import androidx.room.Room
import com.fernandoherrera.lyrics.DATABASE
import com.fernandoherrera.lyrics.model.dao.LyricsDao
import com.fernandoherrera.lyrics.model.db.LyricsDatabase

object DataManager {
    var albums = ArrayList<Song>()
    lateinit var db: LyricsDatabase
    lateinit var lyricsDao: LyricsDao

    fun initDb(context: Context) {
        db = Room.databaseBuilder(
            context,
            LyricsDatabase::class.java, DATABASE
        ).build()
        lyricsDao = db.lyricsDao()
    }
}