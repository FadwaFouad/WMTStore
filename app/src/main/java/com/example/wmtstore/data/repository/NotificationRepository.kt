package com.example.wmtstore.data.repository

import android.content.Context
import com.example.wmtstore.data.models.Notification
import com.example.wmtstore.data.notification_provider.NotificationFromSharedpreference

class NotificationRepository (context : Context){

    private val notificationProvider = NotificationFromSharedpreference(context)

    fun getAllNotifications(): MutableList<Notification>{
        return notificationProvider.getAllNotifications()
    }

    fun deleteNotification(notification: Notification){
        notificationProvider.deleteNotification(notification)
    }
    fun saveNotification(notification: Notification) {
        notificationProvider.saveNotification(notification)
    }



}