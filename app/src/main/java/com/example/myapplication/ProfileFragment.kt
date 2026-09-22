package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etEmail = view.findViewById<EditText>(R.id.etProfileEmail)
        val switchNotifications = view.findViewById<Switch>(R.id.switchProfileNotifications)
        val switchSellerMode = view.findViewById<Switch>(R.id.switchSellerMode)
        val btnSave = view.findViewById<Button>(R.id.btnSaveProfile)

        val sharedPref = requireActivity().getSharedPreferences("KasiCraftsPrefs", Context.MODE_PRIVATE)
        
        // Load values
        val savedEmail = sharedPref.getString("user_email", "")
        etEmail.setText(savedEmail)
        switchNotifications.isChecked = sharedPref.getBoolean("profile_notifications", true)
        switchSellerMode.isChecked = sharedPref.getBoolean("seller_mode", false)

        btnSave.setOnClickListener {
            val email = etEmail.text.toString()
            val notificationsEnabled = switchNotifications.isChecked
            val sellerModeEnabled = switchSellerMode.isChecked

            with(sharedPref.edit()) {
                putString("user_email", email)
                putBoolean("profile_notifications", notificationsEnabled)
                putBoolean("seller_mode", sellerModeEnabled)
                apply()
            }

            Toast.makeText(requireContext(), "Profile Saved!", Toast.LENGTH_SHORT).show()
        }
    }
}