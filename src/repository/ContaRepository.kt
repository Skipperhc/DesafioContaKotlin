package repository

import entity.Conta

class ContaRepository private constructor(){
    companion object {
        private val listaConta = mutableListOf<Conta>()
        fun save(conta: Conta) {
            listaConta.add(conta)
        }

        fun lista() : List<Conta> {
            return listaConta
        }
    }
}