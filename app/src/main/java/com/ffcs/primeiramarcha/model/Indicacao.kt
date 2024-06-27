package com.ffcs.primeiramarcha.model

data class Indicacao(
    val CodigoAssociacao: String,
    val DataCriacao: String?,
    val CpfAssociado: String,
    val EmailAssociado: String,
    val NomeAssociado: String,
    val TelefoneAssociado: String,
    val PlacaVeiculoAssociado: String,
    val NomeAmigo: String,
    val TelefoneAmigo: String,
    val EmailAmigo: String,
    val Observacao: String?
)
