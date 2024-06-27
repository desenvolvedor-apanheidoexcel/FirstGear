package com.ffcs.primeiramarcha.ui.details

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.ffcs.primeiramarcha.R
import com.ffcs.primeiramarcha.databinding.ActivityDetalheOficinaBinding
import com.ffcs.primeiramarcha.model.Oficina
import com.ffcs.primeiramarcha.utils.Utilitarios
import java.io.Serializable

class DetalheOficinaActivity : AppCompatActivity() {
    private var _binding: ActivityDetalheOficinaBinding? = null
    private val binding get() = _binding!!

    lateinit var oficina: Oficina

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetalheOficinaBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val toolbar: Toolbar = findViewById(R.id.topDetailsAppBar)
        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        getIntentOficina()
        setupViews()
        showDetalhesOficina()
    }

    private fun getIntentOficina() {
        if (intent.hasExtra("oficina")) {
            oficina = intent.extras!!.getSerializableCompat("oficina", Oficina::class.java)
        } else {
            finish()
        }
    }

    private fun setupViews() {
        binding.itemDetalheOficina.iconMapaDetalhe.setOnClickListener {
            abreMapa()
        }

        binding.itemDetalheOficina.iconTelefoneDetalhe.setOnClickListener {
            prepararLigacao()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()

        if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showDetalhesOficina() {
        binding.itemDetalheOficina.imgLogo.setImageBitmap(Utilitarios.Base64toBitmap(oficina.Foto))
        binding.itemDetalheOficina.txtNomeOficina.text = oficina.Nome
        binding.itemDetalheOficina.txtDescricaoCurta.text = oficina.DescricaoCurta
        binding.itemDetalheOficina.txtEndereco.text = oficina.Endereco

        if (!oficina.Telefone1.isNullOrEmpty()) {
            binding.itemDetalheOficina.txtTelefone1.visibility = View.VISIBLE
            binding.itemDetalheOficina.txtTelefone1.text = oficina.Telefone1
        } else {
            binding.itemDetalheOficina.txtTelefone1.visibility = View.INVISIBLE
        }

        if (!oficina.Telefone2.isNullOrEmpty()) {
            binding.itemDetalheOficina.txtTelefone2.visibility = View.VISIBLE
            binding.itemDetalheOficina.txtTelefone2.text = oficina.Telefone2
        } else {
            binding.itemDetalheOficina.txtTelefone2.visibility = View.INVISIBLE
        }

        if (oficina.Telefone1.isNullOrEmpty() && oficina.Telefone2.isNullOrEmpty()) {
            binding.itemDetalheOficina.iconTelefoneDetalhe.isEnabled = false
            binding.itemDetalheOficina.iconTelefoneDetalhe.visibility = View.INVISIBLE
        } else {
            binding.itemDetalheOficina.iconTelefoneDetalhe.isEnabled = true
            binding.itemDetalheOficina.iconTelefoneDetalhe.visibility = View.VISIBLE
        }

        binding.itemDetalheOficina.txtDescricaoLonga.text = trataDescricao(
            oficina.Descricao
        )
    }

    private fun prepararLigacao() {
        if (!oficina.Telefone1.isNullOrEmpty()) {
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + oficina.Telefone1)
            startActivity(dialIntent)
        }
    }

    private fun abreMapa() {
        try {
            startActivity(Utilitarios.pinLocationMap(oficina.Latitude, oficina.Longitude))
        } catch (e: ActivityNotFoundException) {
            val uri =
                Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps")
            val i = Intent(Intent.ACTION_VIEW, uri)
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(i)
        }
    }

    private fun trataDescricao(descricao: String): String? {
        val separator = System.getProperty("line.separator")
        return separator?.let { descricao.replace("\\n", it) }
    }

    fun <T : Serializable?> Bundle.getSerializableCompat(key: String, clazz: Class<T>): T {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) getSerializable(
            key,
            clazz
        )!! else (getSerializable(key) as T)
    }

}