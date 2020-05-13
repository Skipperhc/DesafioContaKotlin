package busines

import entity.Conta
import entity.ContaCorrente
import entity.ContaPoupanca
import repository.ContaRepository

class ListagemBusines {

    fun listarCorrentes() : List<Conta> {
        val lisarCorrentes = { ContaRepository.lista().filter { it is ContaCorrente }}
        return lisarCorrentes()
    }

    fun listarPoupanca() : List<Conta> {
        val listarPoupancas = { ContaRepository.lista().filter { it is ContaPoupanca }}
        return listarPoupancas()
    }

    fun listarContas() : List<Conta> {
        return ContaRepository.lista()
    }
}