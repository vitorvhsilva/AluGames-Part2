package org.example.br.com.alura.alugames.modelo

import br.com.alura.alugames.model.Recomendavel
import com.google.gson.annotations.Expose

data class Jogo(@Expose val titulo: String?, @Expose val capa: String?, val preco: Double): Recomendavel {
    var descricao: String? = ""
    private val listaNotas = mutableListOf<Int>()
    override val media: Double
        get() = listaNotas.average()
    override fun recomendar(nota: Int) {
        listaNotas.add(nota)
    }

    override fun toString(): String {
        return "Jogo(titulo='$titulo', capa='$capa', descricao='$descricao')"
    }


}