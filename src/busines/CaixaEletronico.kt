package busines

import entity.Conta
import entity.Operacao
import javax.print.attribute.standard.Destination

class CaixaEletronico {
    private constructor()

    companion object {
        fun pagar(origem: Conta, valor:Double) {
            origem.saldo -= valor
            val op = Operacao(origem.id,valor)
            origem.listaOperacao.add(op)
        }

        fun pagar(origem: Conta, destino: Conta, valor:Double) {
            origem.saldo -= valor
            destino.saldo += valor
            val op = Operacao(origem.id, valor, destino.id)
            origem.listaOperacao.add(op)
            destino.listaOperacao.add(op)
        }
    }
}