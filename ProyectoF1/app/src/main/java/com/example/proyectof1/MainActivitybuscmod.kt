package com.example.proyectof1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class MainActivitybuscmod : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private var currentDocumentId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_activitybuscmod)

        db = FirebaseFirestore.getInstance()
        val txtMascotaInput = findViewById<EditText>(R.id.txt_mascota_input)
        val btnBuscar = findViewById<Button>(R.id.btn_buscar)
        val btnModificar = findViewById<Button>(R.id.btn_citamodificar)
        val btnEliminar = findViewById<Button>(R.id.btn_eliminar)
        val btnRegresarMenu = findViewById<Button>(R.id.btn_regresarmenu)

        btnBuscar.setOnClickListener {
            buscarCita(txtMascotaInput.text.toString())
        }

        btnModificar.setOnClickListener {
            modificarCita()
        }

        btnEliminar.setOnClickListener {
            mostrarDialogoConfirmacion()
        }

        btnRegresarMenu.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun buscarCita(nombreMascota: String) {
        db.collection("citas_medicas")
            .whereEqualTo("nombreMascota", nombreMascota)
            .limit(1)
            .get()
            .addOnSuccessListener { documents ->
                if (!documents.isEmpty) {
                    val document = documents.first()
                    currentDocumentId = document.id
                    findViewById<EditText>(R.id.txt_mascota_result).setText(document.getString("nombreMascota"))
                    findViewById<EditText>(R.id.txt_genero_result).setText(document.getString("genero"))
                    findViewById<EditText>(R.id.txt_doctor_result).setText(document.getString("doctor"))
                    findViewById<EditText>(R.id.txt_fechahora_result).setText(document.getString("fechaHora"))
                    findViewById<EditText>(R.id.txt_motivo_result).setText(document.getString("motivo"))
                } else {
                    showToast("No se encontró una cita para la mascota especificada.")
                }
            }
            .addOnFailureListener {
                showToast("Error al buscar la cita: ${it.message}")
            }
    }

    private fun modificarCita() {
        val nombreMascota = findViewById<EditText>(R.id.txt_mascota_result).text.toString()
        val genero = findViewById<EditText>(R.id.txt_genero_result).text.toString()
        val doctor = findViewById<EditText>(R.id.txt_doctor_result).text.toString()
        val fechaHora = findViewById<EditText>(R.id.txt_fechahora_result).text.toString()
        val motivo = findViewById<EditText>(R.id.txt_motivo_result).text.toString()

        if (currentDocumentId != null) {
            db.collection("citas_medicas").document(currentDocumentId!!)
                .update(mapOf(
                    "nombreMascota" to nombreMascota,
                    "genero" to genero,
                    "doctor" to doctor,
                    "fechaHora" to fechaHora,
                    "motivo" to motivo
                ))
                .addOnSuccessListener {
                    showToast("Cita actualizada exitosamente.")
                }
                .addOnFailureListener {
                    showToast("Error al actualizar la cita: ${it.message}")
                }
        } else {
            showToast("No hay una cita cargada para actualizar.")
        }
    }

    private fun mostrarDialogoConfirmacion() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Eliminar Cita")
        builder.setMessage("¿Estás seguro de que quieres eliminar esta cita?")
        builder.setPositiveButton("Sí") { dialog, which ->
            eliminarCita()
        }
        builder.setNegativeButton("No") { dialog, which ->
            dialog.dismiss()
        }
        builder.create().show()
    }

    private fun eliminarCita() {
        if (currentDocumentId != null) {
            db.collection("citas_medicas").document(currentDocumentId!!)
                .delete()
                .addOnSuccessListener {
                    showToast("Cita eliminada exitosamente.")
                    limpiarResultados()
                }
                .addOnFailureListener { e ->
                    showToast("Error al eliminar la cita: ${e.message}")
                }
        } else {
            showToast("No hay una cita cargada para eliminar.")
        }
    }

    private fun limpiarResultados() {
        findViewById<EditText>(R.id.txt_mascota_result).setText("")
        findViewById<EditText>(R.id.txt_genero_result).setText("")
        findViewById<EditText>(R.id.txt_doctor_result).setText("")
        findViewById<EditText>(R.id.txt_fechahora_result).setText("")
        findViewById<EditText>(R.id.txt_motivo_result).setText("")
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}