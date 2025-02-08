package com.example.myapplication  // Define el paquete donde se encuentra la clase

// Importaciones necesarias
import androidx.recyclerview.widget.RecyclerView  // Clase base para un ViewHolder en un RecyclerView
import com.example.myapplication.databinding.ItemPersonajeBinding  // Enlace con el layout XML de cada ítem
import com.squareup.picasso.Picasso  // Librería para cargar imágenes desde URLs

// ViewHolder para gestionar la visualización de cada personaje en el RecyclerView
class PersonajeViewHolder(

    private val binding: ItemPersonajeBinding  // Recibe un objeto de binding para acceder a los elementos del layout
) : RecyclerView.ViewHolder(binding.root) {  // Extiende de ViewHolder y usa la vista raíz del binding

    // Función que asigna los datos de un personaje a los elementos de la UI
    fun bind(personaje: dataPersonaje) {
        binding.tvNombre.text = personaje.name  // Asigna el nombre del personaje al TextView correspondiente

        // Asigna la especie del personaje, manejando el caso de "unknown"
        binding.tvEspecie.text = if(personaje.species.equals("unknown")) {
            "Especie sin calificar"
        } else {
            "Especie " + personaje.species
        }

        /*  mostrar el género del personaje, lo dejo preparado pero no lo utilizo)
        binding.tvGender.text = if(personaje.gender.equals("unknown")) {
            "Sexo sin calificar"
        } else {
            "Sexo " + personaje.gender
        }
        */

        // Asigna el mundo de origen del personaje, manejando el caso de "unknown"
        binding.tvOrigin.text = if(personaje.origin.name.equals("unknown")) {
            "Mundo sin calificar"
        } else {
            "Mundo " + personaje.origin.name
        }

        /*  mostrar el estado del personaje, lo dejo preparado pero no lo utilizo)
        binding.tvEstado.text = if(personaje.status.equals("unknown")) {
            "En busca"
        } else {
            personaje.status
        }
        */

        // Carga la imagen del personaje con Picasso
        Picasso.get()
            .load(personaje.image)  // URL de la imagen
            .placeholder(R.drawable.ic_launcher_foreground)  // Imagen de espera mientras carga
            .error(R.drawable.ic_launcher_background)  // Imagen de error si falla la carga
            .into(binding.ivPersonaje)  // Asigna la imagen al ImageView
    }
}

