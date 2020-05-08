package entity

abstract class Conta private constructor() {

    constructor(nomeCliente:String, saldo:Double, banco:String) : this() {
        this.nomeCliente = nomeCliente
        this.saldo = saldo
        this.banco = banco
        this.id = qtdContas + 1
        qtdContas += 1
    }

    var nomeCliente:String = ""
    var saldo:Double = 0.0
    var banco:String = ""
    var id:Int = 0
    var listaOperacao = mutableListOf<Operacao>()

    abstract fun valorMax() : Double
    companion object {
        var qtdContas:Int = 0
    }
}