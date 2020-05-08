package entity

class ContaCorrente private constructor(nomeCliente:String, saldo:Double, banco:String)
    : Conta(nomeCliente, saldo, banco) {

    constructor(nomeCliente:String, saldo:Double, banco:String, limite:Double) : this(nomeCliente, saldo, banco) {
        this.limite = limite
    }

    var limite:Double = 0.0

    override fun valorMax() : Double { return saldo + limite }

    override fun toString() : String {
        return "Id: ${id} Nome: ${nomeCliente} | Banco: ${banco} | Saldo: ${saldo} | Limite: ${limite} \n"
    }
}