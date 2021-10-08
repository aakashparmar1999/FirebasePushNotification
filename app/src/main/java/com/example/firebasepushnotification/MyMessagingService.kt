package com.example.firebasepushnotification

import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        showNotification(p0.notification?.title!!, p0.notification!!.body!!)
    }

    private fun showNotification(title: String, message: String) {
        val builder = NotificationCompat.Builder(this, "MyNotification")
            .setContentTitle(title)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setAutoCancel(true)
            .setContentText(message)
        val manager = NotificationManagerCompat.from(this)
        manager.notify(999, builder.build())
    }
}