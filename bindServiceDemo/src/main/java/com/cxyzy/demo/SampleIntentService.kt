package com.cxyzy.demo

import android.app.IntentService
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import kotlin.random.Random


class SampleIntentService : IntentService("download_worker_thread") {
    private val tag = SampleIntentService::class.java.simpleName

    inner class MyBinder : Binder() {

        val service: SampleIntentService
            get() = this@SampleIntentService
    }

    private val binder = MyBinder()
    override fun onHandleIntent(intent: Intent?) {
        log(tag, "onHandleIntent")
    }

    private fun doSomething() {
        log(tag, "doSomething")
        Thread.sleep(2000)
    }

    override fun onBind(intent: Intent?): IBinder? {
        log(tag, "onBind")
        doSomething()
        return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        log(tag, "onUnbind")
        return false
    }


    override fun onDestroy() {
        log(tag, "onDestroy")
        super.onDestroy()
    }

    fun getRandomNumber(): Int {
        return Random.nextInt()
    }

}