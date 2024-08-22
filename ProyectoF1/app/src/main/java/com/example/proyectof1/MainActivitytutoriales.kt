package com.example.proyectof1

import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class MainActivitytutoriales : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_activitytutoriales)

        val videoView = findViewById<VideoView>(R.id.videoView)
        // Configura el MediaController
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)

        // Configura la URI del video
        val videoPath = "android.resource://" + packageName + "/" + R.raw.tu_video
        videoView.setMediaController(mediaController)
        videoView.setVideoPath(videoPath)
        videoView.start()

        videoView.setOnPreparedListener { mp ->
            mp.isLooping = true // Para que el video se repita automáticamente
        }
    }
}