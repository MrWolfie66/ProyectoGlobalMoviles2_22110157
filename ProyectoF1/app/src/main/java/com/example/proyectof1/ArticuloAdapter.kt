package com.example.proyectof1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ArticuloAdapter(
    private val articulos: MutableList<Articulo>,
    private val onDeleteClick: (Int) -> Unit
) : RecyclerView.Adapter<ArticuloAdapter.ArticuloViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticuloViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_articulo, parent, false)
        return ArticuloViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ArticuloViewHolder, position: Int) {
        val articulo = articulos[position]
        holder.itemNameTextView.text = articulo.nombre
        holder.itemPriceTextView.text = "$${articulo.precio}"
        holder.deleteButton.setOnClickListener {
            onDeleteClick(position)
        }
    }

    override fun getItemCount() = articulos.size

    class ArticuloViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemNameTextView: TextView = itemView.findViewById(R.id.itemNameTextView)
        val itemPriceTextView: TextView = itemView.findViewById(R.id.itemPriceTextView)
        val deleteButton: Button = itemView.findViewById(R.id.deleteButton)
    }
}
