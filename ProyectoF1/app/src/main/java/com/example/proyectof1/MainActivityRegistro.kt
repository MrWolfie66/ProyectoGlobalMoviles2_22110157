package com.example.proyectof1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivityRegistro : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_registro)

        // Inicializar FirebaseAuth
        auth = FirebaseAuth.getInstance()

        // Inicializar eventos
        initEvents()
    }

    private fun initEvents() {
        val btnRegistro = findViewById<Button>(R.id.btnRegistro)
        btnRegistro.setOnClickListener {
            val correoEntrada = findViewById<EditText>(R.id.correo_entrada).text.toString()
            val passwordEntrada = findViewById<EditText>(R.id.password_entrada).text.toString()

            if (correoEntrada.isEmpty() || passwordEntrada.isEmpty()) {
                showToast("Por favor, ingresa el correo y la contraseña.")
                return@setOnClickListener
            }

            // Llamada a Firebase para registrar el usuario
            auth.createUserWithEmailAndPassword(correoEntrada, passwordEntrada)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        showToast("Registro exitoso.")
                        // Aquí puedes redirigir al MainActivity u otra actividad si lo deseas
                    } else {
                        showToast("Error al registrar. Inténtalo de nuevo.")
                    }
                }
        }

        val btnRegresarLogin = findViewById<Button>(R.id.btn_regresarlogin)
        btnRegresarLogin.setOnClickListener {
            // Navegar de vuelta al MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()  // Opcional: cerrar esta actividad para que el usuario no pueda regresar a ella con el botón de atrás
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}