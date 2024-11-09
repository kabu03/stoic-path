package hu.bme.aut.qh8vdn.stoicpath.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class NotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.d("NotificationReceiver", "Alarm triggered and received in BroadcastReceiver")
        val serviceIntent = Intent(context, NotificationService::class.java)
        context.startService(serviceIntent)
        Log.d("NotificationReceiver", "NotificationService started from BroadcastReceiver")
    }
}

