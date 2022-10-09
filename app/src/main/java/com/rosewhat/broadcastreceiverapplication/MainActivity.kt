package com.rosewhat.broadcastreceiverapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.rosewhat.broadcastreceiverapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var progressBar: ProgressBar

    private var count = 0
    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == MyReceiver.LOADED) {
                val percent = intent.getIntExtra("percent", 0)
                progressBar.progress = percent
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            Intent(MyReceiver.ACTION_CLICKED).apply {
                putExtra(MyReceiver.EXTRA_COUNT, count++)
                localBroadCastManager.sendBroadcast(this)
            }

        }
        val intent = IntentFilter().apply {
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
            addAction(Intent.ACTION_BATTERY_LOW)
            addAction(MyReceiver.ACTION_CLICKED)
            addAction(MyReceiver.LOADED)
        }
        localBroadCastManager.registerReceiver(receiver, intent)
        Intent(this, MyService::class.java).apply {
            startService(this)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        localBroadCastManager.unregisterReceiver(receiver)
    }
}