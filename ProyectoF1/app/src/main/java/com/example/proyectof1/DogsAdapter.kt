package com.example.proyectof1
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Dog(val breed: String, val description: String)

class DogsAdapter(private val dogList: List<Dog>) : RecyclerView.Adapter<DogsAdapter.DogViewHolder>() {

    class DogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dogIcon: ImageView = itemView.findViewById(R.id.dogIcon)
        val dogBreed: TextView = itemView.findViewById(R.id.dogBreed)
        val dogDescription: TextView = itemView.findViewById(R.id.dogDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_dog, parent, false)
        return DogViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        val currentDog = dogList[position]
        holder.dogBreed.text = currentDog.breed
        holder.dogDescription.text = currentDog.description
        holder.dogIcon.setImageResource(R.drawable.wawacon)  // Asegúrate de tener este recurso en la carpeta drawable
    }

    override fun getItemCount() = dogList.size
}