package com.ffcs.primeiramarcha.ui.recommendation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.ffcs.primeiramarcha.R
import com.ffcs.primeiramarcha.constants.Constants
import com.ffcs.primeiramarcha.databinding.ActivityRecommendationBinding
import com.ffcs.primeiramarcha.model.IndicacaoEnvio
import com.ffcs.primeiramarcha.utils.Utilitarios
import org.koin.android.ext.android.inject

class RecommendationActivity : AppCompatActivity() {
    private var _binding: ActivityRecommendationBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RecommendationViewModel by inject()
    lateinit var indicacaoEnvio: IndicacaoEnvio


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRecommendationBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val toolbar: Toolbar = findViewById(R.id.topRecommendationAppBar)
        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        //Observers
        viewModel.sucessoIndicacao.observe(this) { resultado ->
            resultado?.let {
                //TODO dialogSucess(resultado)
            }
        }

        viewModel.erroIndicacao.observe(this) { resultado ->
            resultado?.let {
                //TODO dialogErro(resultado)
            }
        }

        setupActivity()
    }

    private fun setupActivity() {
        binding.formRecomendacao.txtNumeroCodigoAssociacao.text =
            Constants.codigoAssociacao.toString()
        binding.formRecomendacao.txtDataAtual.text = Utilitarios.actualDateFormated("dd/MM/yyyy")

        binding.formRecomendacao.btnSendRecommendation.setOnClickListener {
            //TODO validar campos e chamar api
        }
    }
}