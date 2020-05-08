package entity

class ContaPoupanca(nomeCliente:String, saldo:Double, banco:String)
    : Conta(nomeCliente, saldo, banco) {

    override fun valorMax(): Double {
        return saldo
    }

    override fun toString() : String {
        return "Id: ${id} Nome: ${nomeCliente} | Banco: ${banco} | Saldo: ${saldo} \n"
    }
}