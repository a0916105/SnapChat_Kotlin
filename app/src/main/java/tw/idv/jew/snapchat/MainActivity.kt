package tw.idv.jew.snapchat

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
//            val channel =
            NotificationChannel(
                    "love",
                    "Channel Love",
                    NotificationManager.IMPORTANCE_HIGH)
            .apply {
                description = "重要的人"
                enableLights(true)
                enableVibration(true)
            }.also {
                val manager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                manager.createNotificationChannel(it)
            }
        }*/
        var fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            makeNotification()
        }
    }

    private fun makeNotification() {
        val channelId = "love"
        val channelName = "我的最愛"
        val manager = notificationManager(channelId, channelName)

        val intent = Intent(this, ChatActivity::class.java)
        val pIntent = PendingIntent.getActivity(this,
                        0,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(this)
            .setSmallIcon(android.R.drawable.ic_popup_reminder)
            .setContentTitle("This is title")
            .setContentText("Testing")
            .setSubText("This is info")
            .setWhen(System.currentTimeMillis())
            .setChannelId(channelId)
                .setContentIntent(pIntent)

        manager.notify(1, builder.build())
    }

    private fun notificationManager(
        channelId: String,
        channelName: String
    ): NotificationManager {
        val manager =
            getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_HIGH
            ).also {
                manager.createNotificationChannel(it)
            }
        }

        return manager
    }
}