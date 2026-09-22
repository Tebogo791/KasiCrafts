package com.example.myapplication

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class LoginValidationTest {
    private fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$".toRegex()
        return email.matches(emailRegex)
    }

    @Test
    fun testEmailValidation_CorrectEmail_ReturnsTrue() {
        assertTrue(isValidEmail("test@kasicrafts.co.za"))
    }

    @Test
    fun testEmailValidation_InvalidEmail_ReturnsFalse() {
        assertFalse(isValidEmail("invalid-email"))
    }
}