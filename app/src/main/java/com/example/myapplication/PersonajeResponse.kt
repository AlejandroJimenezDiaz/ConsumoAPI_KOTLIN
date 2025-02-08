package com.example.myapplication  // Define el paquete donde se encuentra el código

// Creamos data classes para definir la estructura de los datos que manejará la aplicación

// Representa la estructura de un personaje con sus atributos principales
data class dataPersonaje(
    var name: String,  // Nombre del personaje
    var species: String,  // Especie del personaje
    var gender: String,  // Género del personaje
    var image: String,  // URL de la imagen del personaje
    var status: String,  // Estado del personaje
    var origin: Origin  // Objeto que almacena información sobre el origen del personaje
)

// Representa el origen del personaje (mundo del que proviene)
data class Origin(
    var name: String,  // lugar de origen
    var url: String  // URL con información del origen
)

// Representa la respuesta completa de la API al solicitar personajes
data class CharacterResponse(
    var info: Character,  // Información general de la respuesta
    var results: List<dataPersonaje>  // Lista de personajes obtenidos en la consulta
)

// Representa la estructura de un personaje con más detalles
data class Character(
    var id: Int,  // Identificador único del personaje
    var name: String,  // Nombre del personaje
    var status: String,  // Estado del personaje (Vivo, Muerto, Desconocido)
    var species: String,  // Especie del personaje
    var gender: String,  // Género del personaje
    var image: String  // URL de la imagen del personaje
)


