package com.ffcs.primeiramarcha.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.ffcs.primeiramarcha.R
import com.ffcs.primeiramarcha.model.Oficina
import com.ffcs.primeiramarcha.utils.Utilitarios

class MainOficinaAdapter(
    private var context: Context,
    private var listOficinas: ArrayList<Oficina>,
    private val itemOficinaClickListener: OnOficinaClickListener
) : RecyclerView.Adapter<MainOficinaAdapter.ViewHolder>() {

    interface OnOficinaClickListener {
        fun onOficinaClick(oficina: Oficina)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_oficina, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val oficina = listOficinas[position]

        holder.cardOficina.setOnClickListener {
            itemOficinaClickListener.onOficinaClick(oficina)
        }

        holder.txtNomeOficina.text = oficina.Nome
        holder.txtDescricaoCurta.text = oficina.DescricaoCurta
        holder.txtEndereco.text = oficina.Endereco

        if(!oficina.Telefone1.isNullOrEmpty()){
            holder.txtTelefone1.visibility = View.VISIBLE
            holder.txtTelefone1.text = oficina.Telefone1
        }else{
            holder.txtTelefone1.visibility = View.INVISIBLE
        }

        if(!oficina.Telefone2.isNullOrEmpty()){
            holder.txtTelefone2.visibility = View.VISIBLE
            holder.txtTelefone2.text = oficina.Telefone2
        }else{
            holder.txtTelefone2.visibility = View.INVISIBLE
        }

        holder.imgLogo.setImageBitmap(Utilitarios.Base64toBitmap(oficina.Foto))
    }

    fun updateAdapter(listaOficinas: List<Oficina>) {
        listOficinas.clear()
        listOficinas.addAll(listaOficinas)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return listOficinas.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtNomeOficina: com.google.android.material.textview.MaterialTextView = itemView.findViewById(R.id.txtNomeOficina)
        var txtDescricaoCurta: com.google.android.material.textview.MaterialTextView = itemView.findViewById(R.id.txtDescricaoCurta)
        var txtEndereco: com.google.android.material.textview.MaterialTextView = itemView.findViewById(R.id.txtEndereco)
        var txtTelefone1: com.google.android.material.textview.MaterialTextView = itemView.findViewById(R.id.txtTelefone1)
        var txtTelefone2: com.google.android.material.textview.MaterialTextView = itemView.findViewById(R.id.txtTelefone2)
        var imgLogo: ImageView = itemView.findViewById(R.id.imgLogo)

        var cardOficina: com.google.android.material.card.MaterialCardView = itemView.findViewById(R.id.cardOficina)

    }
}