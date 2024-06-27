package com.ffcs.primeiramarcha.interfaces

import com.ffcs.primeiramarcha.model.IndicacaoEnvio
import com.ffcs.primeiramarcha.model.OficinasPesquisa
import com.ffcs.primeiramarcha.model.RetornoErro
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PrimeiraMarchaService {
    @GET("Api/Oficina")
    suspend fun buscaOficinas(
        @Query("codigoAssociacao") codigoAssociacao: Int,
        @Query("cpfAssociado") cpfAssociado: String?
    ): Response<OficinasPesquisa>

    @POST("api/Indicacao")
    suspend fun enviaIndicacao(
        @Body indicacao: IndicacaoEnvio
    ): Response<RetornoErro>

}