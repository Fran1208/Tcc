package com.example.test_tcc_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class tela_opcoes extends AppCompatActivity {

    private Button bt_tela_filme;
    private Button bt_tela_perfil;
    private Button bt_tela_qr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_opcoes);

        getSupportActionBar().hide();

        bt_tela_filme = findViewById(R.id.bt_filmes);
        bt_tela_filme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(tela_opcoes.this,tela_inicial1.class);
                startActivity(intent1);
            }
        });

        bt_tela_perfil = findViewById(R.id.bt_tela_perfil);
        bt_tela_perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(tela_opcoes.this,telaPrincipal.class);
                startActivity(intent2);
            }
        });

        bt_tela_qr = findViewById(R.id.bt_tela_qr);
        bt_tela_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(tela_opcoes.this,tela_qr.class);
                startActivity(intent3);
            }
        });

    }
}