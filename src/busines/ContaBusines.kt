package busines

import entity.Conta
import entity.ContaCorrente
import entity.ContaPoupanca
import entity.Operacao
import entity.exceptions.CamposVaziosException
import entity.exceptions.NenhumItemEncontrado
import entity.exceptions.NumeroNegativoException
import repository.ContaRepository
import java.lang.Exception
import java.lang.IllegalArgumentException
import java.util.*
import javax.swing.JFrame
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
        throw NenhumItemEncontrado("Nenhuma conta foi encontrada")
    }

    fun validarCampos(name:String, saldo:String, banco:String, limite:String = "0") {
        if(name.equals("") || saldo.equals("") || banco.equals("") || limite.equals("")) {
            throw CamposVaziosException("Preencha todos os campos adequadamente")
        }
        try {
            saldo.toDouble()
            limite.toDouble()
            if(saldo.toDouble() < -limite.toDouble()) {
                throw IllegalArgumentException("Insira um saldo adequado")
            }
            if(limite.toDouble() < 0) {
                throw IllegalArgumentException("Insira um limite positivo")
            }
        } catch (excp:NumberFormatException) {
            throw excp
        }
    }
}