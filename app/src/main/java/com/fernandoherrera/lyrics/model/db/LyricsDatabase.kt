package com.fernandoherrera.lyrics.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fernandoherrera.lyrics.Converters
import com.fernandoherrera.lyrics.Lyrics
import com.fernandoherrera.lyrics.model.dao.LyricsDao

@Database(entities = [Lyrics::class], version = 1)
@TypeConverters(Converters::class)
abstract class LyricsDatabase: RoomDatabase() {
    abstract fun lyricsDao(): LyricsDao
}