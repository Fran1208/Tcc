package com.example.test_tcc_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginN extends AppCompatActivity {



    private Button bt_tela_cadastro;
    private EditText edit_email, edit_senha;
    private Button bt_entrar;
    private ProgressBar progressBar;
    String[] mensagens = {"Preencha todos os campos", "Login Efetuado com sucesso"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_n);

        getSupportActionBar().hide();
        IniciarComponentes();


        bt_tela_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(LoginN.this,cadastro.class);
                startActivity(intent);
            }
        });

        bt_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = edit_email.getText().toString();
                String senha = edit_senha.getText().toString();
                if (email.isEmpty() || senha.isEmpty()){
                    Snackbar snackbar = Snackbar.make(v, mensagens[0], Snackbar.LENGTH_LONG);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }else {
                    AutenticarUsuario(v);

                }
            }
        });
    }
    private void AutenticarUsuario(View view){


        String email = edit_email.getText().toString();
        String senha = edit_senha.getText().toString();

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    progressBar.setVisibility(View.VISIBLE);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            TelaInicial();
                        }
                    },4000);
                }else {
                    String erro;

                    try {
                        throw task.getException();
                    }catch (Exception e){
                        erro = "Erro ao logar Usuário";
                    }
                    Snackbar snackbar = Snackbar.make(view,erro, Snackbar.LENGTH_LONG);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser usuarioAtual = FirebaseAuth.getInstance().getCurrentUser();

        if (usuarioAtual !=null ){
            TelaInicial();

        }
    }


    private void TelaInicial (){
        Intent intent = new Intent(LoginN.this,tela_opcoes.class);
        startActivity(intent);
        finish();
    }



    private void IniciarComponentes(){
        bt_tela_cadastro = findViewById(R.id.bt_tela_cadastro);
        edit_email = findViewById(R.id.edit_email);
        edit_senha = findViewById(R.id.edit_senha);
        bt_entrar= findViewById(R.id.bt_entrar);
        progressBar = findViewById(R.id.progress_bar);
    }
}