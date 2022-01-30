package com.fernandoherrera.lyrics.view


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.fernandoherrera.lyrics.ARTIST
import com.fernandoherrera.lyrics.model.DataManager
import com.fernandoherrera.lyrics.SONG_TITLE
import com.fernandoherrera.lyrics.databinding.ActivityLyricsBinding
import com.fernandoherrera.lyrics.model.ApiUtil
import java.net.URL
import java.util.concurrent.Executors

class LyricsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLyricsBinding
    private lateinit var lyricsTextView: TextView
    private lateinit var lyricsTextErrorView: TextView
    private lateinit var spinner: ProgressBar
    private lateinit var artist: String
    private lateinit var songTitle: String
    private lateinit var artistName: TextView
    private lateinit var songName: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLyricsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        DataManager.initDb(applicationContext)


        spinner = binding.lyricsProgressBar
        lyricsTextView = binding.lyricsText
        lyricsTextErrorView = binding.lyricsTextError
        artistName = binding.detailsCard.artistName
        songName = binding.detailsCard.songName

        artist = intent.extras?.get(ARTIST).toString()
        songTitle = intent.extras?.get(SONG_TITLE).toString()

        getLyrics()
    }

    private fun getLyrics() {
        val myExecutor = Executors.newSingleThreadExecutor()
        val myHandler = Handler(Looper.getMainLooper())
        myExecutor.execute {
            val url: URL = ApiUtil.buildSearchLyrics(artist, songTitle)
            val lyricsJson: String? = ApiUtil.getJson(url)
            when {
                lyricsJson === "Not found" -> {
                    myHandler.post {
                        hideSpinner()
                        displayError("No lyrics found")
                    }
                }
                lyricsJson === "Network Error" -> {
                    hideSpinner()
                    displayError("Your network is down.")
                }
                lyricsJson == null -> {
                    myHandler.post {
                        hideSpinner()
                        displayError("No lyrics found")
                    }
                }
                else -> {
                    val lyrics: String = ApiUtil.getLyricsFromJson(lyricsJson)
                    myHandler.post {
                        hideSpinner()
                        displayLyrics(lyrics)

                    }
                }
            }
        }
        myExecutor.shutdown()
    }

    private fun hideSpinner() {
        spinner.visibility = View.GONE
    }

    private fun displayLyrics(lyrics: String) {
        lyricsTextView.visibility = View.VISIBLE
        artistName.text = artist
        songName.text = songTitle
        lyricsTextView.text = lyrics
    }

    private fun displayError(message: String) {
        lyricsTextErrorView.visibility = View.VISIBLE
        lyricsTextErrorView.text = message
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            android.R.id.home -> {
                finish()
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (DataManager.db.isOpen) DataManager.db.close()
    }
}