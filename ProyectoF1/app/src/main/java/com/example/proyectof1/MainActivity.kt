package com.example.proyectof1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private val PREFS_NAME = "MyPrefsFile"
    private val USER_EMAIL_KEY = "user_email"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        // Cargar el usuario guardado, si existe
        loadUserEmail()

        initEvents()
    }

    private fun initEvents() {
        val btnOpenActivity = findViewById<Button>(R.id.btnOpenActivity)
        btnOpenActivity.setOnClickListener {
            val usernameInput = findViewById<EditText>(R.id.username_input).text.toString()
            val passwordInput = findViewById<EditText>(R.id.password_input).text.toString()

            if (usernameInput.isEmpty() || passwordInput.isEmpty()) {
                showToast("Por favor, ingresa el nombre de usuario y la contraseña.")
                return@setOnClickListener
            }

            // Autenticación con Firebase
            auth.signInWithEmailAndPassword(usernameInput, passwordInput)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Guardar el correo del usuario en SharedPreferences
                        saveUserEmail(usernameInput)

                        // Login exitoso, redirigir a MainActivity2
                        val intent = Intent(this, MainActivity2::class.java)
                        startActivity(intent)
                    } else {
                        // Error en el login
                        showToast("Los datos son incorrectos o el usuario no existe.")
                    }
                }
        }

        val btnOpenRegister = findViewById<Button>(R.id.btnOpenRegister)
        btnOpenRegister.setOnClickListener {
            // Abre la actividad de registro
            val intent = Intent(this, MainActivityRegistro::class.java)
            startActivity(intent)
        }
    }

    private fun saveUserEmail(email: String) {
        val sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(USER_EMAIL_KEY, email)
        editor.apply()
    }

    private fun loadUserEmail() {
        val sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val savedEmail = sharedPreferences.getString(USER_EMAIL_KEY, null)
        if (savedEmail != null) {
            // Si se guardó un correo, lo mostramos en el campo de usuario
            findViewById<EditText>(R.id.username_input).setText(savedEmail)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}