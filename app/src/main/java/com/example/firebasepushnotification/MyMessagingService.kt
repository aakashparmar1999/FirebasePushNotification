package com.example.firebasepushnotification

import android.graphics.Color
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyMessagingService : FirebaseMessagingService() {
    val TAG: String = "MyTAG"

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        Log.d(TAG, "onMessageReceived: called")
        if (p0.notification != null) {
            val title = p0.notification!!.title
            val body = p0.notification!!.body
            showNotification(title, body)
        }
        if (p0.data.isNotEmpty()) {
            Log.d(TAG, "onMessageReceived: Data" + p0.data.toString())
            for (key in p0.data.keys) {
                Log.d(TAG, "onMessageReceived: Key:" + key + "Data: " + p0.data[key])
                val ob = MainActivity()
                ob.setText(p0.data.toString())
            }
        }

    }

    private fun showNotification(title: String?, message: String?) {
        Log.d(TAG, "showNotification: called")
        val builder = NotificationCompat.Builder(this, "MyNotification")
            .setContentTitle(title)
            .setSmallIcon(R.drawable.ic_notifications)
            .setAutoCancel(true)
            .setColor(Color.BLUE)
            .setContentText(message)
        val manager = NotificationManagerCompat.from(this)
        manager.notify(999, builder.build())
    }

    override fun onNewToken(p0: String) {
        Log.d(TAG, "onNewToken: called")
        super.onNewToken(p0)
    }

    override fun onDeletedMessages() {
        Log.d(TAG, "onDeletedMessages: called")
        super.onDeletedMessages()
    }
}