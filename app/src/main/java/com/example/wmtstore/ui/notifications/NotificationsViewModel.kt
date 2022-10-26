package com.example.wmtstore.ui.notifications

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.wmtstore.data.models.Notification
import com.example.wmtstore.data.repository.NotificationRepository

class NotificationsViewModel (application : Application): AndroidViewModel(application) {
 private val notificationRepo = NotificationRepository(application)

    fun getAllNotifications(): MutableList<Notification>{
        return notificationRepo.getAllNotifications()
    }

    fun saveNotification(notification: Notification){
        notificationRepo.saveNotification(notification)
    }
    fun deleteNotification(notification: Notification){
        notificationRepo.deleteNotification(notification)
    }
    fun getNotifyLiveData() : MutableLiveData<MutableList<Notification>> = notificationRepo.notifyLiveData

}