package com.example.test_tcc_1.Adapter


import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.test_tcc_1.R
import com.example.test_tcc_1.model.Filme

class AdapterFilme(private val context: Context, private val produtos : MutableList<Filme>): RecyclerView.Adapter <AdapterFilme.FilmeViewHolder> (){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmeViewHolder {
        val itemLista = LayoutInflater.from(context).inflate(R.layout.itens_filmes,parent,false)
        val holder = FilmeViewHolder(itemLista)
        return  holder
    }

    override fun onBindViewHolder(holder: FilmeViewHolder, position: Int) {
        holder.foto.setImageResource(produtos[position].foto)
        holder.nome.text = produtos[position].nome
        holder.descricao.text = produtos[position].descricao
        holder.preco.text = produtos[position].preco
    }

    override fun getItemCount(): Int = produtos.size



    inner class FilmeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foto = itemView.findViewById<ImageView>(R.id.foto_filme)
        val nome = itemView.findViewById<TextView>(R.id.Nome_filme)
        val descricao = itemView.findViewById<TextView>(R.id.Descricao_filme)
        val preco = itemView.findViewById<TextView>(R.id.preco_filme)

    }
}