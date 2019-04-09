package com.cxyzy.demo

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val tag = MainActivity::class.java.simpleName

    private lateinit var service: SampleIntentService
    private var isBound = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initListeners()
    }


    private fun initListeners() {
        bindTv.setOnClickListener {
            val intent = Intent(this, SampleIntentService::class.java)
            bindService(intent, conn, BIND_AUTO_CREATE)
        }
        unBindTv.setOnClickListener { unbindService(conn) }
    }

    private val conn = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, binder: IBinder) {
            log(tag, "onServiceConnected")
            isBound = true
            val myBinder = binder as SampleIntentService.MyBinder
            service = myBinder.service

            val num = service.getRandomNumber()
            log(tag, "getRandomNumber called,result:$num")
        }

        override fun onServiceDisconnected(name: ComponentName) {
            isBound = false
            log(tag, "onServiceDisconnected")
        }
    }
}
