package com.fernandoherrera.lyrics.viewmodel

import androidx.lifecycle.ViewModel
import com.fernandoherrera.lyrics.model.LyricsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext


class MainViewModel(private val repository: LyricsRepository) :
    ViewModel(), CoroutineScope {

    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job


}
