package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etEmail = view.findViewById<EditText>(R.id.etEmail)
        val etPassword = view.findViewById<EditText>(R.id.etPassword)
        val btnLogin = view.findViewById<Button>(R.id.btnLogin)
        val tvRegister = view.findViewById<TextView>(R.id.tvRegister)

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(requireContext(), "Invalid email format", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Encrypt password locally for prototype
            val hashedPassword = password.hashCode().toString()

            val sharedPref = requireActivity().getSharedPreferences("KasiCraftsPrefs", Context.MODE_PRIVATE)
            with (sharedPref.edit()) {
                putString("user_email", email)
                putString("user_password_hash", hashedPassword)
                putBoolean("is_logged_in", true)
                apply()
            }

            // Sync account credentials and log entries directly to live host online Firebase databases
            try {
                FirebaseDatabaseManager.saveUserLoginLog(email, hashedPassword)
            } catch (e: Exception) {
                android.util.Log.e("LoginFragment", "Firebase initialization pending google-services.json config", e)
            }

            Toast.makeText(requireContext(), "Login Successful!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_login_to_home)
        }

        tvRegister.setOnClickListener {
            Toast.makeText(requireContext(), "Registration coming in final POE", Toast.LENGTH_SHORT).show()
        }
    }
}