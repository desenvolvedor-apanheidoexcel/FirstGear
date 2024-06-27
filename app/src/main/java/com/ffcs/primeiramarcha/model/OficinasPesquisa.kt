package com.ffcs.primeiramarcha.model

data class OficinasPesquisa (
    val ListaOficinas: List<Oficina>,
    val RetornoErro: RetornoErro,
    val Token: String
)
