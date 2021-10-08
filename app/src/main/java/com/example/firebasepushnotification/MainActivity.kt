package com.example.firebasepushnotification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.messaging.FirebaseMessaging
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
                NotificationManager.IMPORTANCE_HIGH
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
        bt_sub.setOnClickListener {

            FirebaseMessaging.getInstance().subscribeToTopic("FCM")
                .addOnCompleteListener { task ->
                    var msg = "Subscribed"
                    if (!task.isSuccessful) {
                        msg = "Subscribe failed"
                    }
                    Log.d("TAG", msg)
                    Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                }

        }
        bt_usub.setOnClickListener {

            FirebaseMessaging.getInstance().unsubscribeFromTopic("FCM")
                .addOnCompleteListener { task ->
                    var msg = "Unsubscribed"
                    if (!task.isSuccessful) {
                        msg = "Unsubscribe failed"
                    }
                    Log.d("TAG", msg)
                    Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                }

        }
        /*  if (intent != null && intent.hasExtra("key1")) {
              tv_hello.text = ""
              for (key in intent.extras?.keySet()!!) {
                  try {
                      Log.d(
                          TAG,
                          "onCreate: Key" + key.toString() + "DATA: " + intent.extras!!.getString(key)
                      )
                      //tv_hello.append(intent.extras!!.getString(key))
                  } catch (ex: Exception) {
                      ex.printStackTrace()
                  }

              }
          } */
    }

    fun setText(data: String) {
        tv_hello.text = data
    }
}