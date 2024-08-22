package com.example.proyectof1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity6 : AppCompatActivity() {

    var radio1: RadioButton?=null
    var radio2: RadioButton?=null
    var check1: CheckBox? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        check1=findViewById(R.id.checkBox1)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main6)



        radio1=findViewById(R.id.radioButton)
        radio2=findViewById(R.id.radioButton2)

        val spinner: Spinner = findViewById(R.id.spinneract3)
        val items = listOf("Veterinaria", "Estilista", "Cremacion")

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = items[position]
                Toast.makeText(this@MainActivity6, "$selectedItem", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
        initEvents()
        var btnvalidar: Button = findViewById(R.id.btnvalidar)
        btnvalidar.setOnClickListener { validar() }
    }
    fun initEvents(){
        val button1 = findViewById<Button>(R.id.btnRegresar)
        button1.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }
    private fun validar(){
        val nombre: String = findViewById<EditText>(R.id.txt_nombre).text.toString()
        val spinner: Spinner = findViewById(R.id.spinneract3)
        val selectedArea: String = spinner.selectedItem.toString()

        var cad:String="Seleccionado: \n"
        var cad1:String="Las Aclaraciones y Responsabilidades han sido aceptadas \n"

        if (radio1?.isChecked==true){
            cad+="Macho"
        }
        if (radio2?.isChecked==true){
            cad+="Hembra"
        }
        if (check1?.isChecked==true){
            cad1+=". "
        }
        Toast.makeText(this, "Nombre ingresado: $nombre", Toast.LENGTH_SHORT).show()
        Toast.makeText(this, "$cad", Toast.LENGTH_LONG).show()
        Toast.makeText(this, "Área seleccionada: $selectedArea", Toast.LENGTH_SHORT).show()
        Toast.makeText(this, "$cad1", Toast.LENGTH_LONG).show()
    }
}