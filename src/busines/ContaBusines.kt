package busines

import entity.Conta
import entity.ContaCorrente
import entity.ContaPoupanca
import entity.Operacao
import repository.ContaRepository
import java.lang.Exception
import javax.swing.JOptionPane

class ContaBusines {
    fun save(name:String, saldo:String, banco:String) {
        validarCampos(name,saldo,banco)
        val conta:Conta = ContaPoupanca(name, saldo.toDouble(), banco)
        ContaRepository.save(conta)
    }
    fun save(name:String, saldo:String, banco:String, limite: String) {
        validarCampos(name,saldo,banco,limite)

        val conta:Conta = ContaCorrente(name, saldo.toDouble(), banco, limite.toDouble())
        ContaRepository.save(conta)
    }

    fun search(id:String) : Conta {
        val idAlterado = id.toInt()
        for(con:Conta in ContaRepository.lista()) {
            if(con.id == idAlterado) {
                return con
            }
        }
        return throw Exception("Nenhuma conta encontrada")
    }

    fun pagar(valor:String, conta:Conta) {
        validarPagamento(valor,conta)

        CaixaEletronico.pagar(conta, valor.toDouble())

        throw Exception("Pagamento efetuado com sucesso")
    }

    fun pagar(valor:String, origem:Conta, destino:Conta) {
        validarPagamento(valor,origem)

        val op = Operacao(origem.id, valor.toDouble(), destino.id)
        origem.listaOperacao.add(op)
        destino.listaOperacao.add(op)
        throw Exception("Pagamento efetuado com sucesso")
    }

    fun validarPagamento(valor:String, con:Conta) {
        try {
            if(valor.toDouble() < 0) {
                throw Exception("Valor não pode ser negativo")
            }
            if(con.valorMax() < valor.toDouble()) {
                throw Exception("Conta não tem saldo suficiente")
            }
            valor.toDouble()
        } catch (e:Exception) {
            throw Exception(e.message)
        }
    }

    fun validarBusca(id:String, nome:String) {
        if(id.equals("") || nome.equals("")) {
            throw Exception("Preencha todos os campos adequadamente")
        }
        try {
            id.toInt()
        } catch (excp:Exception) {
            throw Exception("Insira um ID numerico")
        }
    }

    fun validarCampos(name:String, saldo:String, banco:String, limite:String = "0") {
        if(name.equals("") || saldo.equals("") || banco.equals("") || limite.equals("")) {
            throw Exception("Preencha todos os campos adequadamente")
        }
        try {
            saldo.toDouble()
            limite.toDouble()
        } catch (excp:Exception) {
            throw Exception("Insira numeros adequados")
        }
        if(saldo.toDouble() < 0 || limite.toDouble() < 0) {
            throw Exception("Insira um numero não negativo")
        }
    }

}