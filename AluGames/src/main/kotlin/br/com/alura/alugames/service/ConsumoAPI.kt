package br.com.alura.alugames.service

import br.com.alura.alugames.model.Gamer
import br.com.alura.alugames.model.InfoGamer
import br.com.alura.alugames.utils.criaGamer
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.example.br.com.alura.alugames.modelo.InfoJogo
import org.example.br.com.alura.alugames.modelo.Jogo
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers

class ConsumoAPI {

    fun consomeDados(endereco: String): String {
        val client: HttpClient = HttpClient.newHttpClient()
        val request = HttpRequest.newBuilder()
            .uri(URI.create(endereco))
            .build()
        val response = client.send(request, BodyHandlers.ofString())

        return response.body()
    }

    fun buscarJogo(id: String): InfoJogo? {
        val json = consomeDados("https://www.cheapshark.com/api/1.0/games?id=$id")

        val gson = Gson()

        var meuInfoJogo: InfoJogo? = null

        val resultado = runCatching {
            meuInfoJogo = gson.fromJson(json, InfoJogo::class.java)
        }

        resultado.onFailure {
            println("Jogo inexistente. Tente outro id.")
            return null
        }

        return meuInfoJogo
    }

    fun buscarGamers(): List<Gamer> {
        val json = consomeDados("https://raw.githubusercontent.com/jeniblodev/arquivosJson/main/gamers.json")


        val gson = Gson()
        val meuGamerTipo = object : TypeToken<List<InfoGamer>>() {}.type

        var listaGamer: List<InfoGamer> = gson.fromJson(json, meuGamerTipo)

        return listaGamer.map { i -> i.criaGamer() }
    }

    fun buscarJogos(): List<Jogo> {
        val json = consomeDados("https://raw.githubusercontent.com/jeniblodev/arquivosJson/main/jogos.json")

        val gson = Gson()
        val meuJogoTipo = object : TypeToken<List<Jogo>>() {}.type

        return gson.fromJson(json, meuJogoTipo)
    }
}