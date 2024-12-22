package br.com.alura.alugames.main

import br.com.alura.alugames.service.ConsumoAPI
import com.google.gson.GsonBuilder
import java.io.File

fun main() {
    val consumo = ConsumoAPI()
    val listaGamers = consumo.buscarGamers()
    val listaJogoJson = consumo.buscarJogos()

//    val gamerCaroline = listaGamers.get(3)
//    val jogoResidentVillage = jogosApi.get(10)
//
//    println(gamerCaroline)
//    println(jogoResidentVillage)
//
//    gamerCaroline.alugaJogo(jogoResidentVillage, Periodo(LocalDate.now(), LocalDate.now().plusDays(7)))
//    println(gamerCaroline.jogosAlugados)
//
    val gamerCamila = listaGamers.get(5)
//    gamerCamila.plano = PlanoAssinatura("PRATA", 9.90, 3, 0.15)
//
//    gamerCamila.recomendar(7)
//    gamerCamila.recomendar(6)
//    gamerCamila.recomendar(10)
//
//    println(gamerCamila)

    val gamerCaroline = listaGamers.get(3)
    val jogoResidentVillage = listaJogoJson.get(10)
    val jogoSpider = listaJogoJson.get(13)
    val jogoTheLastOfUs = listaJogoJson.get(2)
    val jogoDandara = listaJogoJson.get(5)
    val jogoAssassins = listaJogoJson.get(4)
    val jogoCyber = listaJogoJson.get(6)
    val jogoGod = listaJogoJson.get(7)
    val jogoSkyrim = listaJogoJson.get(18)

    gamerCamila.recomendarJogo(jogoResidentVillage, 7)
    gamerCamila.recomendarJogo(jogoTheLastOfUs, 10)
    gamerCamila.recomendarJogo(jogoAssassins, 8)
    gamerCamila.recomendarJogo(jogoCyber, 7)
    gamerCamila.recomendarJogo(jogoGod, 10)
    gamerCamila.recomendarJogo(jogoDandara, 8)
    gamerCamila.recomendarJogo(jogoSkyrim, 8)
    gamerCamila.recomendarJogo(jogoSpider, 6)

    val gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    val serializacao = gson.toJson(gamerCamila.jogosRecomendados)
    println(serializacao)

    val arquivo = File("jogosRecomendados-${gamerCamila.nome}.json")
    arquivo.writeText(serializacao)
    println(arquivo.absolutePath)

}