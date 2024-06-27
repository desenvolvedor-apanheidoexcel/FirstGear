package com.ffcs.primeiramarcha.model

import java.io.Serializable

data class Oficina(
    val Id : Int,
    val Nome: String,
    val Descricao: String,
    val DescricaoCurta: String,
    val Endereco: String,
    val Latitude: String,
    val Longitude: String,
    val Foto: String,
    val AvaliacaoUsuario: Int,
    val CodigoAssociacao: Int,
    val Email: String,
    val Telefone1: String?,
    val Telefone2: String?,
    val Ativo: Boolean
) : Serializable
