package com.example.wmtstore.ui.account

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.wmtstore.R
import com.example.wmtstore.databinding.FragmentProfileBinding
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var profileContainer: LinearLayout
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val user: FirebaseUser? = auth.currentUser

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // define login container
        profileContainer = binding.profileContainer
        // declare variables
        binding.username.text = user?.displayName
        binding.email.text = user?.email

        // load image
        Glide.with(this)
            .load(user?.photoUrl)
            .into(binding.displayImage)

        // sign out user btn
        binding.signoutBtn.setOnClickListener {
            AuthUI.getInstance()
                .signOut(requireContext())
                .addOnCompleteListener { // user is now signed out
                    showLoginScreen()
                }
        }
      
}
    private fun showLoginScreen() {
        // user successfully sign out
        childFragmentManager.beginTransaction()
            .replace(R.id.login_container, AccountFragment())
            .commitNow()

        // hidden initial container
        profileContainer.visibility = View.GONE
    }
}