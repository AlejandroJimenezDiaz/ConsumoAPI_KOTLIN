package com.example.myapplication  // Define el paquete donde está la interfaz

// Importaciones necesarias para Retrofit
import retrofit2.Response  // Permite manejar respuestas HTTP con códigos de estado
import retrofit2.http.GET  // Anotación para solicitudes HTTP de tipo GET
import retrofit2.http.Query  // Permite agregar parámetros a la URL en una solicitud

// Definimos la interfaz que Retrofit usará para comunicarse con la API
interface ApiService {

    // Función para obtener personajes por nombre
    @GET("api/character")  // Indica que esta solicitud es de tipo GET a la URL "api/character"
    suspend fun getCharactersByName  // Función suspendida para llamadas asíncronas
                (@Query("name") name: String)  // Parámetro de la URL (ej: ?name=Rick)
            : CharacterResponse  // Devuelve un objeto CharacterResponse (debes definir esta clase)

    // Función para obtener personajes paginados
    @GET("api/character")  // Realiza una solicitud GET a la misma URL "api/character"
    suspend fun getCharacters(@Query("page") page: Int)  // Parámetro de la URL para paginación (ej: ?page=2)
            : Response<CharacterResponse>  // Devuelve una respuesta HTTP con un CharacterResponse

}