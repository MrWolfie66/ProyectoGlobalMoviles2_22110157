package com.example.proyectof1
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TercerFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var dogsAdapter: DogsAdapter
    private val dogList = listOf(
        Dog("Golden Retriever", "Amistoso, Inteligente, Devoto"),
        Dog("Labrador", "Extrovertido, Temperamento equilibrado, Gentil"),
        Dog("Pastor Alemán", "Confiado, Valiente, Inteligente"),
        Dog("Beagle", "Curioso, Amigable, Determinado"),
        Dog("Bulldog", "Dócil, Amistoso, Decidido"),
        Dog("Poodle", "Inteligente, Activo, Alerta"),
        Dog("Rottweiler", "Leal, Protector, Confiado"),
        Dog("Yorkshire Terrier", "Valiente, Enérgico, Leal"),
        Dog("Boxer", "Juguetón, Activo, Alegre"),
        Dog("Dachshund", "Valiente, Curioso, Amigable"),
        Dog("Shih Tzu", "Cariñoso, Juguetón, Extrovertido"),
        Dog("Doberman", "Leal, Valiente, Enérgico"),
        Dog("Pug", "Afectuoso, Juguetón, Encantador"),
        Dog("Chihuahua", "Alerta, Enérgico, Valiente"),
        Dog("Border Collie", "Trabajador, Inteligente, Energético"),
        Dog("Schnauzer", "Valiente, Alerta, Leal"),
        Dog("Cocker Spaniel", "Cariñoso, Juguetón, Inteligente"),
        Dog("Dálmata", "Enérgico, Amigable, Juguetón"),
        Dog("San Bernardo", "Amistoso, Paciente, Gentil"),
        Dog("Husky Siberiano", "Amistoso, Enérgico, Trabajador")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_tercer, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewDogs)
        recyclerView.layoutManager = LinearLayoutManager(context)
        dogsAdapter = DogsAdapter(dogList)
        recyclerView.adapter = dogsAdapter

        return view
    }
}