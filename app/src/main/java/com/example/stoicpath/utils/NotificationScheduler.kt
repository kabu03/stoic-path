package com.example.stoicpath.utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.util.Log
import com.example.stoicpath.services.NotificationReceiver
import java.util.Calendar

class NotificationScheduler {

    fun scheduleDailyNotification(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val alarmIntent = Intent(context, NotificationReceiver::class.java).let { intent ->
            PendingIntent.getBroadcast(
                context,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        }

        // Check exact alarm permission for Android 12 and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && !alarmManager.canScheduleExactAlarms()) {
            Log.d("NotificationScheduler", "Exact alarm permission missing, directing to settings")
            val intent = Intent(
                Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM,
                Uri.parse("package:${context.packageName}")
            )
            context.startActivity(intent)
            return
        }

        // Set the alarm time to 9 AM Budapest time (GMT+1)
        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 9)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
            // Adjust for the next day if the time has already passed for today
            if (before(Calendar.getInstance())) {
                add(Calendar.DAY_OF_YEAR, 1)
            }
        }

        // Schedule the alarm to trigger daily at 9 AM
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            alarmIntent
        )
        Log.d("NotificationScheduler", "Alarm scheduled for 9 AM Budapest time")
    }
}

/* THIS IS FOR TESTING PURPOSES.
        Log.d("NotificationScheduler", "Scheduling alarm to trigger in 10 seconds")
        val triggerTime = System.currentTimeMillis() + 10 * 1000
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            triggerTime,
            alarmIntent
        )
        Log.d("NotificationScheduler", "Alarm scheduled successfully")
 */


