package com.example.wmtstore.ui.notifications

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.wmtstore.R
import com.example.wmtstore.data.models.Notification
import java.util.*

class NotificationAdapter(
    val context: Context,
    val notificationsList: MutableList<Notification>,
    val notificationsViewModel: NotificationsViewModel
) :
    RecyclerView.Adapter<NotificationViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_notification, parent, false)
        return NotificationViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val notification = notificationsList[position]

        //show date
        val date = Date(notification.time)
        holder.date.text = date.toString()

        //show message
        holder.message.text = notification.message

        // show menu for notification
        holder.moreBtn.setOnClickListener {
            showPopupMenu(holder, notification)
        }


    }

    private fun showPopupMenu(
        holder: NotificationViewHolder,
        notification: Notification
    ) {
        val popupMenu = PopupMenu(context, holder.moreBtn)
        popupMenu.inflate(R.menu.notification_menu)
        popupMenu.show()
        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.delete -> {
                    notificationsViewModel.deleteNotification(notification)
                    Toast.makeText(context, "Delete Notification", Toast.LENGTH_LONG).show()
                    return@setOnMenuItemClickListener true
                }
                else -> false
            }
        }
    }

    override fun getItemCount() = notificationsList.size
}

class NotificationViewHolder(item: View) : RecyclerView.ViewHolder(item) {
    val date: TextView = item.findViewById(R.id.date)
    val message: TextView = item.findViewById(R.id.message)
    val moreBtn: ImageButton = item.findViewById(R.id.more_btn)
}