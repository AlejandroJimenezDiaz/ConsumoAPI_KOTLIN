package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PersonajeAdapter
    private val personajes = mutableListOf<dataPersonaje>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        //---------------------------------------------------------//
        this.initRecyclerview()

        cargarTodasPaginas()

        binding.svPersonaje.setOnQueryTextListener(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //-----------------------------------------------------------//
    }

    private fun cargarTodasPaginas() {
        for (i in 1..42)
            cargarPersonajes(i)
    }

    private fun cargarPersonajes(page: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val call: Response<CharacterResponse> = getRetrofit()
                .create(ApiService::class.java)
                .getCharacters(page)

            val response = call.body()
            runOnUiThread {
                if (call.isSuccessful && response != null) {
                    personajes.addAll(response.results)  // Cargar personajes de la primera página
                    adapter.notifyDataSetChanged()
                } else {
                    showError()
                }
            }
        }
    }

    private fun initRecyclerview() {
        adapter = PersonajeAdapter(personajes)
        binding.rvPersonajes.layoutManager = LinearLayoutManager(this)
        binding.rvPersonajes.adapter = adapter
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/") // URL de la API de Rick and Morty
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            buscarPersonaje(query.trim().toLowerCase())
        }
        return true
    }

    private fun buscarPersonaje(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val characterResponse: CharacterResponse = getRetrofit()
                .create(ApiService::class.java)
                .getCharactersByName(query) // Buscar personajes por nombre

            val personajesEncontrados = characterResponse.results
            runOnUiThread {
                if (personajesEncontrados.isNotEmpty() && personajesEncontrados != null) {
                    personajes.clear()
                    personajes.addAll(personajesEncontrados)
                    adapter.notifyDataSetChanged()
                } else {
                    showError()
                }
            }
        }

    }

    private fun showError() {
        Toast.makeText(this, "Ha ocurrido un error en la petición", Toast.LENGTH_SHORT).show()
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}