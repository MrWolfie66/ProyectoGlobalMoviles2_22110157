package com.example.proyectof1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        initEvents()
    }
    fun initEvents(){
        val button1 = findViewById<Button>(R.id.btn_1)
        val button2 = findViewById<Button>(R.id.btn_2)
        val button3 = findViewById<Button>(R.id.btn_3)
        val button4 = findViewById<Button>(R.id.btn_4)
        val button5 = findViewById<Button>(R.id.btn_5)
        val button6 = findViewById<Button>(R.id.btn_6)
        val button7 = findViewById<Button>(R.id.btn_7)
        val button8 = findViewById<Button>(R.id.btn_8)

        val btnSalir: Button = findViewById(R.id.btn_salir)
        button1.setOnClickListener {
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
        }
        button2.setOnClickListener {
            val intent = Intent(this, MainActivity4::class.java)
            startActivity(intent)
        }
        button3.setOnClickListener {
            val intent = Intent(this, MainActivitycreacion::class.java)
            startActivity(intent)
        }
        button4.setOnClickListener {
            val intent = Intent(this, MainActivitybuscmod::class.java)
            startActivity(intent)
        }
        button5.setOnClickListener {
            val intent = Intent(this, MainActivitylocali::class.java)
            startActivity(intent)
        }
        button6.setOnClickListener {
            val intent = Intent(this, MainActivitycamara::class.java)
            startActivity(intent)
        }
        button7.setOnClickListener {
            val intent = Intent(this, MainActivitytutoriales::class.java)
            startActivity(intent)
        }
        button8.setOnClickListener {
            val intent = Intent(this, MainActivity7::class.java)
            startActivity(intent)
        }
        btnSalir.setOnClickListener {

            finishAffinity()
        }
    }
}