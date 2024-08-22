package com.example.proyectof1

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.MediaPlayer
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivitycamara : AppCompatActivity(), SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private var proximitySensor: Sensor? = null
    private lateinit var sensorTextView: TextView
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_activitycamara)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        sensorTextView = findViewById(R.id.sensor_input)

        // Initialize MediaPlayer for proximity alert sound
        mediaPlayer = MediaPlayer.create(this, R.raw.alert_sound)

        val btnAbrirCamara = findViewById<Button>(R.id.btn_abrircamara)
        btnAbrirCamara.setOnClickListener {
            abrirCamara()
        }

        val btnRegresarMenu = findViewById<Button>(R.id.btn_regresarmenu)
        btnRegresarMenu.setOnClickListener {
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        proximitySensor?.also { proximity ->
            sensorManager.registerListener(this, proximity, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            val distance = it.values[0]
            sensorTextView.text = "Proximidad: $distance cm"
            if (distance < proximitySensor!!.maximumRange / 2) {
                sensorTextView.setBackgroundColor(Color.RED)
                showToast("Por favor, retírese del teléfono")
                playSound()
            } else {
                sensorTextView.setBackgroundColor(Color.GREEN)
                stopSound()
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // No es necesario implementar nada aquí para este caso
    }

    private fun abrirCamara() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE)
        } catch (e: Exception) {
            Toast.makeText(this, "Error al abrir la cámara: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun playSound() {
        mediaPlayer?.start()
    }

    private fun stopSound() {
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.pause()
            mediaPlayer?.seekTo(0)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    companion object {
        private const val CAMERA_REQUEST_CODE = 1
    }
}