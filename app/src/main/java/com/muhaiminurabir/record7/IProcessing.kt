package com.muhaiminurabir.record7

import android.content.Intent

interface IProcessing {
    fun onCreate()
    fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int
    fun onDestroy()
}