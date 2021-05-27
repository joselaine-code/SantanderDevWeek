package br.com.joselaine.santanderdevweek.data

data class Conta(
    val numero: Int,
    val agencia: Int,
    val saldo: Double,
    val limite: Double,
    val cliente: Cliente,
    val cartao: Cartao
) {
}