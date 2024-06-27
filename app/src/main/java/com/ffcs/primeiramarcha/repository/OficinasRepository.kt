package com.ffcs.primeiramarcha.repository

import com.ffcs.primeiramarcha.constants.Constants
import com.ffcs.primeiramarcha.interfaces.PrimeiraMarchaService
import com.ffcs.primeiramarcha.model.Indicacao
import com.ffcs.primeiramarcha.model.IndicacaoEnvio
import com.ffcs.primeiramarcha.model.Oficina
import com.ffcs.primeiramarcha.model.RetornoErro

sealed class Resultado<out R> {
    data class Sucesso<out T>(val dado: T?) : Resultado<T?>()
    data class Erro(val exception: Exception) : Resultado<Nothing>()
}

class OficinasRepository(
    private val service: PrimeiraMarchaService
) {
    suspend fun buscaOficinas(): Resultado<List<Oficina>?> {
        return try {
            val resposta = service.buscaOficinas(codigoAssociacao = Constants.codigoAssociacao, cpfAssociado = null)
            if (resposta.isSuccessful) {
                Resultado.Sucesso(dado = resposta.body()?.ListaOficinas)
            } else {
                Resultado.Erro(exception = java.lang.Exception("Falha ao buscar oficinas"))
            }
        } catch (e: Exception) {
            Resultado.Erro(exception = java.lang.Exception("Falha ao buscar oficinas"))
        }
    }

    suspend fun enviaIndicacao(indicacao: IndicacaoEnvio): Resultado<String?> {
        return try{
            val resposta = service.enviaIndicacao(indicacao)
            if(resposta.isSuccessful){
                Resultado.Sucesso(dado = resposta.body()?.Sucesso)
            }else{
                Resultado.Erro(java.lang.Exception(resposta.message()))
            }

        }catch (e: Exception){
            Resultado.Erro(exception = java.lang.Exception("Falha ao enviar indicação." ))
        }
    }
}