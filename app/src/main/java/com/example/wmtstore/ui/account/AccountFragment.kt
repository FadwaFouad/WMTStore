package com.example.wmtstore.ui.account

import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import com.example.wmtstore.R
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.google.firebase.auth.FirebaseAuth

class AccountFragment : Fragment() {

    private lateinit var viewModel: AccountViewModel
    private lateinit var initialContainer: LinearLayout
    private val signingLauncher =
        registerForActivityResult(FirebaseAuthUIActivityResultContract())
        { result ->
            if (result != null) {
                if (result.resultCode == Activity.RESULT_OK) {
                   showProfile()
                }
            } else {
                Toast.makeText(context, "Error Occurred, Try Again", Toast.LENGTH_LONG).show()
            }

        }

    private fun showProfile() {
        // user successfully sign in
        childFragmentManager.beginTransaction()
            .replace(R.id.container, ProfileFragment())
            .commitNow()

        // hidden initial container
        initialContainer.visibility = View.GONE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // init initial container
        initialContainer = view.findViewById(R.id.initial_layout)

        // show profile if user registered
        var user = FirebaseAuth.getInstance().currentUser
        if (user != null )
            showProfile()

        val signingBtn = view.findViewById<Button>(R.id.signin_btn)
        signingBtn.setOnClickListener {
            val providers = listOf(
                AuthUI.IdpConfig.EmailBuilder().build(),
                AuthUI.IdpConfig.GoogleBuilder().build(),
            )
            val intent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setTheme(R.style.Theme_WMTStore)
                .build()

            //start the authentication flow
            signingLauncher.launch(intent)

        }
    }


}