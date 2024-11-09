package com.example.stoicpath.services
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.stoicpath.R
import com.example.stoicpath.activities.MainActivity

class NotificationService : Service() {
    private val CHANNEL_ID = "motivational_notification_channel"

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Motivational Notifications"
            val descriptionText = "Daily motivational notifications at 9 AM Budapest Time"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
            Log.d("NotificationService", "Notification channel created with ID: $CHANNEL_ID")
        }
    }


    override fun onCreate() {
        super.onCreate()
        Log.d("NotificationService", "Service created")
        createNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("NotificationService", "onStartCommand called")
        createNotificationChannel() // Ensure the channel exists
        sendNotification()
        Log.d("NotificationService", "Notification sent")
        return START_STICKY
    }

    private fun sendNotification() {
        val notificationText = "Rise and shine! Embrace the new day with a stoic mindset."

        // Open MainActivity when the notification is tapped
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Good Morning, Stoic!")
            .setContentText(notificationText)
            .setSmallIcon(R.drawable.ic_motivation)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, notification) // 1 is the notification ID

        Log.d("NotificationService", "sendNotification: Notification created and sent with ID 1")
    }


    override fun onBind(intent: Intent?): IBinder? {
        return null  // This service is unbound
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("NotificationService", "Service destroyed")
    }
}

