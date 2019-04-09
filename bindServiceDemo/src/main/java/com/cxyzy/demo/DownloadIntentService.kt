package com.cxyzy.demo

import android.app.IntentService
import android.content.Intent
import android.util.Log

class DownloadIntentService : IntentService("download_worker_thread") {
    private val tag = DownloadIntentService::class.java.simpleName
    override fun onHandleIntent(intent: Intent?) {
        log("onNewIntent")
        downloadImage()
    }

    private fun downloadImage() {
        log("downloadImage")
        Thread.sleep(2000)
    }

    override fun onCreate() {
        log("onCreate")
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        log("onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        log("onDestroy")
        super.onDestroy()
    }

    private fun log(message: String) {
        Log.d(tag, "$message ${Thread.currentThread().name}")
    }
}