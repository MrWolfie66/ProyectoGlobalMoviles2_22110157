package com.example.proyectof1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button
import android.widget.TextView

class MainActivity7 : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var totalPriceTextView: TextView
    private lateinit var addButton: Button
    private lateinit var articuloAdapter: ArticuloAdapter
    private var articulos: MutableList<Articulo> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main7)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.recyclerView)
        totalPriceTextView = findViewById(R.id.totalPriceTextView)
        addButton = findViewById(R.id.addButton)

        // Inicializa el RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        articuloAdapter = ArticuloAdapter(articulos) { position ->
            eliminarArticulo(position)
        }
        recyclerView.adapter = articuloAdapter

        // Configura el botón "Agregar"
        addButton.setOnClickListener {
            agregarArticulo(Articulo("Marca Premium", 25.0)) // Puedes cambiar el nombre y precio
        }

        // Añade algunos artículos para probar
        agregarArticulo(Articulo("Pedigree 1", 10.0))
        agregarArticulo(Articulo("DogChow 2", 15.0))
        agregarArticulo(Articulo("Campeon 3", 20.0))
    }

    private fun agregarArticulo(articulo: Articulo) {
        articulos.add(articulo)
        articuloAdapter.notifyDataSetChanged()
        actualizarTotalPrecio()
    }

    private fun eliminarArticulo(position: Int) {
        articulos.removeAt(position)
        articuloAdapter.notifyItemRemoved(position)
        actualizarTotalPrecio()
    }

    private fun actualizarTotalPrecio() {
        val total = articulos.sumOf { it.precio }
        totalPriceTextView.text = "Total: $${String.format("%.2f", total)}"
    }
}
