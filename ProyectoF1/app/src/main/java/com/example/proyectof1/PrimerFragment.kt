package com.example.proyectof1
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PrimerFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var dogsAdapter: DogsAdapter
    private val dogList = listOf(
        Dog("Golden Retriever", "Amistoso, Inteligente, Devoto"),
        Dog("Labrador", "Extrovertido, Temperamento equilibrado, Gentil"),
        Dog("Pastor Alemán", "Confiado, Valiente, Inteligente")
        // Agrega más perros
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_primer, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewDogs)
        recyclerView.layoutManager = LinearLayoutManager(context)
        dogsAdapter = DogsAdapter(dogList)
        recyclerView.adapter = dogsAdapter

        return view
    }
}