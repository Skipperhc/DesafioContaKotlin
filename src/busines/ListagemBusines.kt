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

    fun qtdContas() : String {
        var listaContas = ContaRepository.lista()
        if(listaContas.size == 0) return "Nenhuma conta cadastrada"
        if(listaContas.size == 1) return "Mostrando a única conta cadastrada"
        if(listaContas.size >= 2) return "Mostrando as ${listaContas.size} contas cadastradas"

        return "Isso nunca vai aparecer mesmo"
    }
}