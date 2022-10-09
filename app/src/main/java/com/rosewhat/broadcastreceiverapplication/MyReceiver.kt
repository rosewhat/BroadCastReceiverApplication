package com.rosewhat.broadcastreceiverapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast


class MyReceiver : BroadcastReceiver() {



    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            LOADED -> {
                val percent = intent.getIntExtra("percent", 0)
                Toast.makeText(context, percent , Toast.LENGTH_SHORT).show()

            }
            ACTION_CLICKED -> {
                val count = intent.getIntExtra(EXTRA_COUNT, 0)
                Toast.makeText(context, "Clicked $count", Toast.LENGTH_SHORT).show()
            }
            Intent.ACTION_AIRPLANE_MODE_CHANGED -> {
                Toast.makeText(context, AIRPLANE_MODE_CHANGED, Toast.LENGTH_SHORT).show()
            }
            Intent.ACTION_BATTERY_LOW -> {
                // yes or no
                val turnedOn = intent.getBooleanExtra("state", false)
                Toast.makeText(context, ACTION_BATTERY_LOW + turnedOn, Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        private const val AIRPLANE_MODE_CHANGED = "Airplane mode changed "
        private const val ACTION_BATTERY_LOW = "Battery Low"
        const val ACTION_CLICKED = "clicked"
        const val EXTRA_COUNT = "count"
        const val LOADED = "loaded"
    }
}