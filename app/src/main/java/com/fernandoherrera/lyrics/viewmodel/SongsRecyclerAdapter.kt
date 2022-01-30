package com.fernandoherrera.lyrics.viewmodel

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.fernandoherrera.lyrics.ARTIST
import com.fernandoherrera.lyrics.R
import com.fernandoherrera.lyrics.SONG_TITLE
import com.fernandoherrera.lyrics.model.Song
import com.fernandoherrera.lyrics.view.LyricsActivity
import com.squareup.picasso.Picasso

class SongsRecyclerAdapter(private val context: Context, private val Songs: List<Song>) : Adapter<SongsRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val songTitle: TextView = view.findViewById(R.id.songTitle)
        val artist: TextView = view.findViewById(R.id.artist)
        private val picture:ImageView = view.findViewById(R.id.imageView2)

        fun bind(song : Song) {
            artist.text = song.artist
            songTitle.text = song.songTitle
            Picasso.get().load(song.picture).into(picture)
        }

        init {
            view.setOnClickListener {
                val intent = Intent(context, LyricsActivity::class.java)
                intent.putExtra(ARTIST, artist.text)
                intent.putExtra(SONG_TITLE, songTitle.text)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
                .inflate(R.layout.search_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val song = Songs[position]

        holder.bind(song)
    }

    override fun getItemCount(): Int {
        return Songs.size
    }
}