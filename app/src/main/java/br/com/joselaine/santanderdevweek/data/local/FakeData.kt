package br.com.joselaine.santanderdevweek.data.local

import br.com.joselaine.santanderdevweek.data.Cartao
import br.com.joselaine.santanderdevweek.data.Cliente
import br.com.joselaine.santanderdevweek.data.Conta

class FakeData(){
    fun getLocalData(): Conta {
        val cliente = Cliente("Joselaine Aparecida")
        val cartao = Cartao(1705)
        return Conta(123422 - 5, 2654, 500.0, 1030.0, cliente, cartao)
    }

}