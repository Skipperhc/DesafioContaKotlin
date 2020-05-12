package entity

import java.util.*

class Operacao private constructor(var contaIdOrigem:Int?, val contaIdDestino:Int,
                                   val totalTransferencia:Double, val id:Int = numeroOperacao) {
    companion object {
        var numeroOperacao = 1
    }

    fun vitor() {
        contaIdOrigem?.div(2)
    }
    
    constructor(origem:Int, valor:Double, destino:Int = 0) :
            this(contaIdDestino = destino,contaIdOrigem = origem, totalTransferencia = valor) {
        numeroOperacao++
    }
}