package com.ffcs.primeiramarcha.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ffcs.primeiramarcha.model.Oficina
import com.ffcs.primeiramarcha.repository.OficinasRepository
import com.ffcs.primeiramarcha.repository.Resultado
import kotlinx.coroutines.launch

class MainViewModel(private val repository: OficinasRepository) : ViewModel() {
    private val mOficinasLiveData = MutableLiveData<List<Oficina>?>()
    private val mError = MutableLiveData<Boolean>()

    val oficinas: MutableLiveData<List<Oficina>?>
        get() = mOficinasLiveData

    val error: LiveData<Boolean>
        get() = mError

    fun fech() {
        viewModelScope.launch {
            try {
                when (val result = repository.buscaOficinas()) {
                    is Resultado.Sucesso -> {
                        mOficinasLiveData.postValue(result.dado)
                    }

                    is Resultado.Erro -> {
                        mError.postValue(true)
                    }
                }
            } catch (e: Exception) {
                mError.postValue(true)
            }
        }
    }

}