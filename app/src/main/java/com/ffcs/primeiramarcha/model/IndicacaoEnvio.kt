package com.ffcs.primeiramarcha.model

data class IndicacaoEnvio(
    val Indicacao: Indicacao,
    val Remetente: String,
    val Copias: List<String>
)
