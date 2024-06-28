package com.ffcs.primeiramarcha.ui.recommendation

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.ffcs.primeiramarcha.R
import com.ffcs.primeiramarcha.constants.Constants
import com.ffcs.primeiramarcha.databinding.ActivityRecommendationBinding
import com.ffcs.primeiramarcha.model.Indicacao
import com.ffcs.primeiramarcha.model.IndicacaoEnvio
import com.ffcs.primeiramarcha.utils.Utilitarios
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.android.ext.android.inject


class RecommendationActivity : AppCompatActivity() {
    private var _binding: ActivityRecommendationBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RecommendationViewModel by inject()

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
            binding.formRecomendacao.btnSendRecommendation.isEnabled = true
            limpaCampos()
            resultado?.let {
                dialogMaterialCreator(msg = resultado, isSucess = true)
            }
        }

        viewModel.erroIndicacao.observe(this) { resultado ->
            binding.formRecomendacao.btnSendRecommendation.isEnabled = true
            resultado?.let {
                dialogMaterialCreator(msg = resultado, isSucess = false)
            }
        }

        setupActivity()
    }

    private fun setupActivity() {
        binding.formRecomendacao.txtNumeroCodigoAssociacao.text =
            Constants.codigoAssociacao.toString()
        binding.formRecomendacao.txtDataAtual.text = Utilitarios.actualDateFormated("dd/MM/yyyy")

        binding.formRecomendacao.btnSendRecommendation.setOnClickListener {
            enviaDados()
        }
    }

    private fun enviaDados() {
        val cpfAssociado = binding.formRecomendacao.edtCpfAssociado.text.toString()
        val emailAssociado = binding.formRecomendacao.edtEmailAssociado.text.toString()
        val nomeAssociado = binding.formRecomendacao.edtNomeAssociado.text.toString()
        val telefoneAssociado = binding.formRecomendacao.edtTelefoneAssociado.text.toString()
        val placaVeiculoAssociado = binding.formRecomendacao.edtPlacaAssociado.text.toString()
        val nomeAmigo = binding.formRecomendacao.edtNomeAmigo.text.toString()
        val telefoneAmigo = binding.formRecomendacao.edtTelefoneAmigo.text.toString()
        val emailAmigo = binding.formRecomendacao.edtEmailAmigo.text.toString()
        val observacao = binding.formRecomendacao.edtObservacao.text.toString()

        if (cpfAssociado.isEmpty() || emailAssociado.isEmpty() || nomeAssociado.isEmpty() ||
            telefoneAssociado.isEmpty() || placaVeiculoAssociado.isEmpty() ||
            nomeAmigo.isEmpty() || telefoneAmigo.isEmpty() || emailAmigo.isEmpty()
        ) {
            dialogMaterialCreator("Favor preencher todos os campos.", false)

        } else {
            val dados = IndicacaoEnvio(
                Indicacao(
                    CodigoAssociacao = Constants.codigoAssociacao.toString(),
                    DataCriacao = Utilitarios.actualDateFormated("yyyy-MM-dd"),
                    CpfAssociado = cpfAssociado,
                    EmailAssociado = emailAssociado,
                    NomeAssociado = nomeAssociado,
                    TelefoneAssociado = telefoneAssociado,
                    PlacaVeiculoAssociado = placaVeiculoAssociado,
                    NomeAmigo = nomeAmigo,
                    TelefoneAmigo = telefoneAmigo,
                    EmailAmigo = emailAmigo,
                    Observacao = observacao
                ),
                Remetente = Constants.remetente,
                Copias = listOf<String>()
            )

            binding.formRecomendacao.btnSendRecommendation.isEnabled = false
            viewModel.sendRecommendation(dados)
        }
    }

    private fun dialogMaterialCreator(msg: String, isSucess: Boolean) {
        val alertMaterialDialog =
            MaterialAlertDialogBuilder(this@RecommendationActivity, R.style.IconStyle)
                .setTitle("Aviso")
                .setMessage(msg)
                .setPositiveButton(
                    "Ok"
                ) { _, _ -> }
                .create()
        if (isSucess) {
            alertMaterialDialog.setIcon(R.drawable.check_circle)
        } else {
            alertMaterialDialog.setIcon(R.drawable.error)
        }

        alertMaterialDialog.show()
    }

    private fun limpaCampos() {
        binding.formRecomendacao.txtDataAtual.text = Utilitarios.actualDateFormated("dd/MM/yyyy")
        binding.formRecomendacao.edtCpfAssociado.text?.clear()
        binding.formRecomendacao.edtEmailAssociado.text?.clear()
        binding.formRecomendacao.edtNomeAssociado.text?.clear()
        binding.formRecomendacao.edtTelefoneAssociado.text?.clear()
        binding.formRecomendacao.edtPlacaAssociado.text?.clear()
        binding.formRecomendacao.edtNomeAmigo.text?.clear()
        binding.formRecomendacao.edtTelefoneAmigo.text?.clear()
        binding.formRecomendacao.edtEmailAmigo.text?.clear()
        binding.formRecomendacao.edtObservacao.text?.clear()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()

        if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}