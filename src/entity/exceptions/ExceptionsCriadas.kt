package entity.exceptions

import java.lang.RuntimeException

class CamposVaziosException(mensagem:String) : RuntimeException(mensagem)

class NenhumItemEncontrado(mensagem: String) : RuntimeException(mensagem)

class NumeroNegativoException(mensagem: String) : RuntimeException(mensagem)
