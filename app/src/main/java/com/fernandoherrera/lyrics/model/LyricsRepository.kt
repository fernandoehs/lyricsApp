package com.fernandoherrera.lyrics.model

import com.fernandoherrera.lyrics.Lyrics
import com.fernandoherrera.lyrics.model.dao.LyricsDao

class LyricsRepository(private val lyricsDao: LyricsDao) {
    fun findByName(artistName: String, songName: String): Lyrics = lyricsDao.findByName(artistName, songName)
    fun getAll(): List<Lyrics> = lyricsDao.getAll()
    fun insertLyrics(vararg lyrics: Lyrics) =lyricsDao.insertLyrics( )
    fun getLyric(lyricId: Int): Lyrics = lyricsDao.getLyric(lyricId)
    fun updateSavedLyricsById(vararg lyrics: Lyrics) = lyricsDao.updateSavedLyricsById( )
}