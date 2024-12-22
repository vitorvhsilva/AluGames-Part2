package br.com.alura.alugames.model

interface Recomendavel {
    val media: Double

    fun recomendar(nota: Int)
}