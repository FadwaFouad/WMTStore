package com.example.wmtstore.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wmtstore.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {

    private lateinit var binding: FragmentNotificationsBinding
    private lateinit var notificationsViewModel: NotificationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)
        binding = FragmentNotificationsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        notificationsViewModel.getNotifyLiveData().observe(viewLifecycleOwner){
            val notifyList = notificationsViewModel.getAllNotifications()
            if (notifyList.isNotEmpty())
                initRecycleView()
            else {
                // no notifications
                binding.notificationsList.visibility = View.GONE
                binding.notFound.visibility = View.VISIBLE

            }
        }
        // add horizontal line after each item
        binding.notificationsList.addItemDecoration(DividerItemDecoration(context,RecyclerView.VERTICAL))
    }

    private fun initRecycleView() {
        // make list visible
        binding.notificationsList.visibility = View.VISIBLE
        binding.notFound.visibility = View.GONE

        // init recycle view
        binding.notificationsList.layoutManager = LinearLayoutManager(context)
        binding.notificationsList.adapter = NotificationAdapter(
            requireContext(),
            notificationsViewModel.getAllNotifications(),
            notificationsViewModel
        )
    }


}