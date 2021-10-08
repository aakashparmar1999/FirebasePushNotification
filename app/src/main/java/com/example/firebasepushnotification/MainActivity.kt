package com.example.firebasepushnotification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val TAG = "Main Activity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "MyNotification",
                "MyNotification",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
        Firebase.messaging.subscribeToTopic("general")
            .addOnCompleteListener { task ->
                var msg = "msg_subscribed"
                if (!task.isSuccessful) {
                    msg = "msg_subscribe_failed"
                }
                Log.d("TAG", msg)
                Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
            }
        if (intent != null && intent.hasExtra("key1")) {
            tv_hello.text = ""
            for (key in intent.extras?.keySet()!!) {
                try {
                    Log.d(
                        TAG,
                        "onCreate: Key" + key.toString() + "DATA: " + intent.extras!!.getString(key)
                    )
                    tv_hello.append(intent.extras!!.getString(key))
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }

            }
        }
    }
}