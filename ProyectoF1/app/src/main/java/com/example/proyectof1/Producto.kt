package com.example.proyectof1
/*Este NOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO*/
import java.io.Serializable

data class Producto(
    var idProducto: String,
    var nomProducto: String,
    var descripcion: String,
    var precio: Double
): Serializable
