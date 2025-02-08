package com.example.myapplication  // Define el paquete donde se encuentra la clase

// Importaciones necesarias para trabajar con RecyclerView y ViewBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemPersonajeBinding  // Importa el binding del layout de cada ítem

// Adaptador para el RecyclerView, recibe una lista de personajes
class PersonajeAdapter(private val personajes: List<dataPersonaje>) :
    RecyclerView.Adapter<PersonajeViewHolder>() {  // Extiende de RecyclerView.Adapter con el ViewHolder correspondiente

    // Método que se ejecuta cuando se necesita crear un nuevo ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonajeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)  // Obtiene un LayoutInflater desde el contexto del padre
        return PersonajeViewHolder(ItemPersonajeBinding.inflate(layoutInflater, parent, false))
        // Infla el diseño del ítem y lo envuelve en un ViewHolder
    }

    // Método que se ejecuta para actualizar los datos de un ViewHolder con un personaje específico
    override fun onBindViewHolder(holder: PersonajeViewHolder, position: Int) {
        val personaje = personajes[position]  // Obtiene el personaje en la posición dada
        holder.bind(personaje)  // Llama al método `bind()` del ViewHolder para asignar los datos al layout
    }

    // Método que devuelve el número total de elementos en la lista
    override fun getItemCount(): Int = personajes.size
}
