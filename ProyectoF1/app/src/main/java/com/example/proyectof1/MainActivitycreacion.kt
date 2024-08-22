package com.example.proyectof1

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.firestore.FirebaseFirestore

class MainActivitycreacion : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_activitycreacion)

        db = FirebaseFirestore.getInstance()

        createNotificationChannel()  // Call to create a notification channel

        val btnCrearCita = findViewById<Button>(R.id.btn_citacrear)
        val btnRegresarMenu = findViewById<Button>(R.id.btn_regresarmenu)

        btnCrearCita.setOnClickListener {
            crearCita()
        }

        btnRegresarMenu.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun crearCita() {
        val mascotaInput = findViewById<EditText>(R.id.mascota_input).text.toString()
        val doctorInput = findViewById<EditText>(R.id.doctor_input).text.toString()
        val fechaHoraInput = findViewById<EditText>(R.id.fechahora_input).text.toString()
        val motivoInput = findViewById<EditText>(R.id.motivo_input).text.toString()
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val selectedGenderId = radioGroup.checkedRadioButtonId
        val genderRadioButton = findViewById<RadioButton>(selectedGenderId)
        val gender = if (genderRadioButton != null) genderRadioButton.text.toString() else ""

        if (mascotaInput.isEmpty() || doctorInput.isEmpty() || fechaHoraInput.isEmpty() || motivoInput.isEmpty() || gender.isEmpty()) {
            showToast("Por favor, llena todos los campos.")
            return
        }

        val nuevaCita = hashMapOf(
            "nombreMascota" to mascotaInput,
            "genero" to gender,
            "doctor" to doctorInput,
            "fechaHora" to fechaHoraInput,
            "motivo" to motivoInput
        )

        db.collection("citas_medicas")
            .add(nuevaCita)
            .addOnSuccessListener {
                showToast("Cita creada exitosamente.")
                sendNotification(fechaHoraInput)
                clearFields()
            }
            .addOnFailureListener { e ->
                showToast("Error al crear la cita: ${e.message}")
            }
    }

    private fun sendNotification(fecha: String) {
        val notificationId = 1
        val channelId = "CHANNEL_ID"
        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.tremendoperrito)
            .setContentTitle("Cita Creada")
            .setContentText("La cita se ha generado exitosamente, con la siguiente fecha: $fecha")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setStyle(NotificationCompat.BigTextStyle().bigText("La cita se ha generado exitosamente, con la siguiente fecha: $fecha"))
            .setAutoCancel(true)  // Notificación se cancela al hacer clic en ella

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        NotificationManagerCompat.from(this).notify(1, builder.build())
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "CHANNEL_ID"
            val channelName = "Channel Name"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, channelName, importance)
            channel.description = "Channel Description"
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun clearFields() {
        findViewById<EditText>(R.id.mascota_input).text.clear()
        findViewById<EditText>(R.id.doctor_input).text.clear()
        findViewById<EditText>(R.id.fechahora_input).text.clear()
        findViewById<EditText>(R.id.motivo_input).text.clear()
        findViewById<RadioGroup>(R.id.radioGroup).clearCheck()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}