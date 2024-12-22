package br.com.alura.alugames.model

import org.example.br.com.alura.alugames.modelo.Jogo

data class Aluguel(val gamer: Gamer, val jogo: Jogo, val periodo: Periodo) {
    val valorDoAluguel = gamer.plano.obterValor(this)

    override fun toString(): String {
        return "Aluguel do jogo ${jogo.titulo} por ${gamer.nome} pelo valor R$${valorDoAluguel}"
    }
}
