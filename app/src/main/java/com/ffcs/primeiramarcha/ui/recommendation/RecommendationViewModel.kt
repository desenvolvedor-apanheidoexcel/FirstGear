package com.ffcs.primeiramarcha.ui.recommendation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ffcs.primeiramarcha.model.IndicacaoEnvio
import com.ffcs.primeiramarcha.repository.OficinasRepository
import com.ffcs.primeiramarcha.repository.Resultado
import kotlinx.coroutines.launch

class RecommendationViewModel(private val repository: OficinasRepository) : ViewModel() {
    private val mSucessoLiveData = MutableLiveData<String?>()
    private val mErrorLiveData = MutableLiveData<String?>()

    val sucessoIndicacao: MutableLiveData<String?>
        get() = mSucessoLiveData

    val erroIndicacao: LiveData<String?>
        get() = mErrorLiveData

    fun sendRecommendation(indicacao: IndicacaoEnvio) {
        viewModelScope.launch {
            try {
                when (val result = repository.enviaIndicacao(indicacao)) {
                    is Resultado.Sucesso -> {
                        mSucessoLiveData.postValue(result.dado)
                    }

                    is Resultado.Erro -> {
                        mErrorLiveData.postValue(result.exception.message)
                    }
                }
            } catch (e: Exception) {
                mErrorLiveData.postValue("Falha ao enviar indicação.")
            }
        }
    }

}