package br.com.alura.alugames.model

import org.example.br.com.alura.alugames.modelo.Jogo
import java.util.*
import kotlin.random.Random

data class Gamer(var nome: String, var email: String): Recomendavel {
    var dataNasc: String? = null
    var usuario: String? = null
        set (value) {
            field = value

            if (idInterno.isNullOrBlank()) criarIdInterno()
        }
    var idInterno: String? = null
        private set

    var plano: Plano = PlanoAvulso("BRONZE")
    val jogosBuscados = mutableListOf<Jogo>()
    val jogosAlugados = mutableListOf<Aluguel>()
    val listaNotas = mutableListOf<Int>()
    val jogosRecomendados = mutableListOf<Jogo>()

    fun recomendarJogo(jogo: Jogo, nota: Int) {
        jogo.recomendar(nota)
        jogosRecomendados.add(jogo)
    }

    override val media: Double
        get() = listaNotas.average()

    constructor(nome: String, email: String, dataNasc: String, usuario: String):
            this(nome, email) {
        this.dataNasc = dataNasc
        this.usuario = usuario
        criarIdInterno()
    }

    init {
        if (this.nome.isNullOrBlank()) throw IllegalArgumentException("Nome invalido!")
        this.email = validarEmail()
    }


    fun criarIdInterno() {
        val numero = Random.nextInt(10000)
        val tag = String.format("%04d", numero)

        idInterno = "$usuario#$tag"
    }

    fun validarEmail(): String{
        val regex = Regex(pattern = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
        if(regex.matches(email)){
            return email
        }
        throw IllegalArgumentException("Email inválido")
    }

    fun alugaJogo(jogo: Jogo, periodo: Periodo): Aluguel {
        val aluguel = Aluguel(this, jogo, periodo)
        jogosAlugados.add(aluguel)
        return aluguel
    }

    fun jogosDoMes(mes:Int): List<Jogo> {
        return jogosAlugados
            .filter { aluguel ->  aluguel.periodo.dataInicial.monthValue == mes}
            .map { aluguel ->  aluguel.jogo}
    }

    override fun recomendar(nota: Int) {
        if (nota < 1 || nota > 10) {
            println("A nota colocada não é valida")
            return
        }
        listaNotas.add(nota)
    }

    override fun toString(): String {
        return "Gamer(nome='$nome', email='$email', dataNasc=$dataNasc, usuario=$usuario, idInterno=$idInterno, reputacao=$media)"
    }

    companion object {
        fun criarGamer(leitura: Scanner): Gamer {
            println("Boas vindas ao AluGames! Vamos fazer seu cadastro. Digite seu nome:")
            val nome = leitura.nextLine()
            println("Digite seu e-mail:")
            val email = leitura.nextLine()
            println("Deseja completar seu cadastro com usuário e data de nascimento? (S/N)")
            val opcao = leitura.nextLine()

            if (opcao.equals("s", true)) {
                println("Digite sua data de nascimento(DD/MM/AAAA):")
                val nascimento = leitura.nextLine()
                println("Digite seu nome de usuário:")
                val usuario = leitura.nextLine()

                return Gamer(nome, email, nascimento, usuario)
            }

            return Gamer (nome, email)
        }
    }
}
