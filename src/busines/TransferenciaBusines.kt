package busines

import entity.Conta
import entity.exceptions.NumeroNegativoException

class TransferenciaBusines {
    fun pagar(valor:String, conta: Conta) {
        validarPagamento(valor,conta)

        CaixaEletronico.pagar(valor = valor.toDouble(), origem = conta)
    }

    fun pagar(valor:String, origem: Conta, destino: Conta) {
        validarPagamento(valor,origem)

        CaixaEletronico.pagar(valor = valor.toDouble(), destino = destino, origem = origem)
    }

    fun validarPagamento(valor:String, con:Conta) {
        try {
            var valorDouble = valor.toDouble()
            if(valorDouble < 0) {
                throw NumeroNegativoException("Valor não pode ser negativo")
            }
            if(con.valorMax() < valorDouble) {
                throw IllegalArgumentException("Conta não tem saldo suficiente")
            }
        } catch (e:NumberFormatException) {
            throw e
        }
    }
}