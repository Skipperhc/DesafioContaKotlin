package busines

import entity.Conta
import entity.ContaCorrente
import entity.ContaPoupanca
import repository.ContaRepository

class ListagemBusines {

    fun listarCorrentes() : List<Conta> {
        val listaContas = mutableListOf<Conta>()
        for (con:Conta in ContaRepository.lista()) {
            if(con is ContaCorrente) {
                con.limite
                listaContas.add(con)
            }
        }
        return listaContas
    }

    fun listarPoupanca() : List<Conta> {
        val listaContas = mutableListOf<Conta>()
        for (con:Conta in ContaRepository.lista()) {
            if(con is ContaPoupanca) listaContas.add(con)
        }
        return listaContas
    }

    fun listarContas() : List<Conta> {
        return ContaRepository.lista()
    }
}