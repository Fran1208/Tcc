package com.example.test_tcc_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test_tcc_1.Adapter.AdapterFilme
import com.example.test_tcc_1.model.Filme

class tela_inicial1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial1)

        supportActionBar!!.hide()

        val recyclerView_filmes = findViewById<RecyclerView>(R.id.recyclerView_filmes)
        recyclerView_filmes.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)
        recyclerView_filmes.setHasFixedSize(true)
        // configuracao adapter

        val ListaFilmes : MutableList<Filme> = mutableListOf()
        val adapterFilme = AdapterFilme(this,ListaFilmes )
        recyclerView_filmes.adapter = adapterFilme

        val filme1 = Filme(
            R.drawable.cruella,
            nome = "Cruella (157)",
            descricao = "Inteligente, criativa e determinada, Estella quer fazer um nome para si através de seus designs e acaba chamando a atenção da Baronesa Von Hellman.",
            preco = "Inteira: R$30 / Meia: R$15"
        )

        ListaFilmes.add(filme1)

        val filme2 = Filme(
            R.drawable.godzilla,
            nome = "Godzilla vs Kong (169)",
            descricao = "Kong e seus protetores embarcam em uma jornada perigosa para encontrar seu verdadeiro lar.",
            preco = "Inteira: R$30 / Meia: R$15"
        )

        ListaFilmes.add(filme2)

        val filme3 = Filme(
            R.drawable.venom2,
            nome = "Venom 2 (666)",
            descricao = "O relacionamento entre Eddie e Venom está evoluindo.Esses dois lados descobrem como viver juntos.",
            preco = "Inteira: R$30 / Meia: R$15"
        )
        ListaFilmes.add(filme3)

        val filme4 = Filme(
            R.drawable.sobrenatural_a,
            nome = "Sobrenatural 3 (395)",
            descricao = "Elise Rainier é chamada para resolver o caso de uma assombração no Novo México, mas descobre que a atividade sobrenatural está acontecendo. ",
            preco = "Inteira: R$30 / Meia: R$15"
        )
        ListaFilmes.add(filme4)

        val filme5 = Filme(
            R.drawable.invocacao_do_mal,
            nome = "Invocação do MAL 3 (442)",
            descricao = "O casal Ed e Lorraine Warren investigam um assassino que alega ter sido possuído por um demônio.",
            preco = "Inteira: R$30 / Meia: R$15"
        )
        ListaFilmes.add(filme5)
    }
}