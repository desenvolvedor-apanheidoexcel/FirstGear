package com.ffcs.primeiramarcha.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.ffcs.primeiramarcha.R
import com.ffcs.primeiramarcha.databinding.ActivityMainBinding
import com.ffcs.primeiramarcha.model.Oficina
import com.ffcs.primeiramarcha.ui.details.DetalheOficinaActivity
import com.ffcs.primeiramarcha.ui.recommendation.RecommendationActivity
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(),
    MainOficinaAdapter.OnOficinaClickListener {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by inject()
    private lateinit var oficinasAdapter: MainOficinaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = findViewById(R.id.topAppBar)
        setSupportActionBar(toolbar)

        //Observers
        viewModel.oficinas.observe(this) { oficinas ->
            oficinas?.let {
                binding.swiperefresh.isRefreshing = false
                binding.rvOficinas.visibility = View.VISIBLE
                binding.llFalha.visibility = View.GONE
                setupRecyclerView(oficinas)
            }
        }

        viewModel.error.observe(this) { isError ->
            binding.swiperefresh.isRefreshing = false
            binding.rvOficinas.visibility = View.GONE
            binding.llFalha.visibility = View.VISIBLE
        }

        setupActivity()
        getOficinas()
    }

    private fun setupActivity() {

        oficinasAdapter = MainOficinaAdapter(
            this@MainActivity,
            arrayListOf(),
            this@MainActivity
        )

        binding.rvOficinas.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = oficinasAdapter
        }

        binding.swiperefresh.setOnRefreshListener {
            binding.swiperefresh.isRefreshing = true
            getOficinas()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()

        if (id == R.id.action_indique) {
            goToActivityIndique()
            return true
        }

        return super.onOptionsItemSelected(item)

    }

    private fun getOficinas() {
        viewModel.fech()
    }

    private fun setupRecyclerView(oficinas: List<Oficina>) {
        oficinasAdapter = MainOficinaAdapter(
            this@MainActivity,
            oficinas as ArrayList<Oficina>,
            this@MainActivity
        )

        binding.rvOficinas.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = oficinasAdapter
        }
    }

    private fun getErroRequest() {
        oficinasAdapter.updateAdapter(arrayListOf())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onOficinaClick(oficina: Oficina) {
        val intent = Intent(this, DetalheOficinaActivity::class.java)
        intent.putExtra("oficina", oficina)
        startActivity(intent)
    }

    private fun goToActivityIndique() {
        val intent = Intent(this, RecommendationActivity::class.java)
        startActivity(intent)
    }
}