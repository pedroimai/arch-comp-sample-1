package com.pedroimai.archcomp

import android.app.Application
import androidx.emoji.text.EmojiCompat
import androidx.emoji.bundled.BundledEmojiCompatConfig

class ArchCompApp : Application() {
    private lateinit var executors: AppExecutors

    override fun onCreate() {
        super.onCreate()
        executors = AppExecutors()
        initEmojiCompat()
    }

    fun getRepository(): ContactRepository {
        return ContactRepository.getInstance(ContactApi.getInstance(executors))
    }

    private fun initEmojiCompat(){
        val config = BundledEmojiCompatConfig(this)
        EmojiCompat.init(config)
    }
}
