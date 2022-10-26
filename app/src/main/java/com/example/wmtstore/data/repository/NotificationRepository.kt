package com.example.wmtstore.data.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.wmtstore.data.models.Notification
import com.example.wmtstore.data.models.Product
import com.example.wmtstore.data.notification_provider.NotificationFromSharedpreference

class NotificationRepository(context: Context) {

    private val notificationProvider = NotificationFromSharedpreference(context)
    //
    private var notifyList = mutableListOf<Notification>()
    val notifyLiveData = MutableLiveData<MutableList<Notification>>(notifyList)


    fun getAllNotifications(): MutableList<Notification> {
        notifyList = notificationProvider.getAllNotifications()
        return notifyList
    }

    fun deleteNotification(notification: Notification) {
        notifyList.remove(notification)
        notificationProvider.deleteNotification(notification)
        notifyChange()
    }



    fun saveNotification(notification: Notification) {
        notifyList.add(notification)
        notificationProvider.saveNotification(notification)
        notifyChange()
    }

    private fun notifyChange() {
        notifyLiveData.value= notifyList
    }


}