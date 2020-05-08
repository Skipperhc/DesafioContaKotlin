package entity

import java.util.*

class Operacao private constructor() {
    companion object {
        var numeroOperacao = 0
    }

    constructor(origem:Int, valor:Double, destino:Int = 0) : this() {
        this.id = numeroOperacao + 1
        numeroOperacao++
        this.contaIdOrigem = origem
        this.contaIdDestino = destino
        this.totalTransferencia = valor
    }
    var id:Int = 0
        get() { return field } private set(value) { }
    var contaIdOrigem:Int = 0
        get() { return field } private set(value) { field = value}
    var contaIdDestino:Int = 0
        get() { return field } private set(value) {field = value}
    var totalTransferencia:Double = 0.0
        get() { return field } private set(value) {field = value}
}