package com.example.belstom

import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.util.concurrent.TimeUnit

class MessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
//        val title = remoteMessage.notification?.title.toString()
//        val message = remoteMessage.notification?.body.toString()
        val data = remoteMessage.data
        val title = data["title_msg"]
        val message = data["content_msg"]
        title?.let { titleCheck ->
            message?.let { messageCheck ->
                workManagerReminder(titleCheck, messageCheck) }
        }
    }

    private fun workManagerReminder(
        title: String,
        message: String
    ) {
        val myData: Data = Data.Builder()
            .putString(applicationContext.resources.getString(R.string.uploadWorker_title), title)
            .putString(
                applicationContext.resources.getString(R.string.uploadWorker_message),
                message
            )
            .build()


        val myWorkRequest = OneTimeWorkRequest.Builder(UploadWorker::class.java)
            .setInitialDelay(1000, TimeUnit.MILLISECONDS)
            .setInputData(myData)
            .build()
        WorkManager.getInstance().enqueue(myWorkRequest)
    }







}