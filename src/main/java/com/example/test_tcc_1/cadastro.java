package com.example.test_tcc_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class cadastro extends AppCompatActivity {

    private EditText edit_nome, edit_email, edit_senha;
    private Button botao_para_cadastrar;
    private Button bt_voltar;
    String[] mensagens = {"Preencha todos os campos", "Cadastro realizado com sucesso"};
    String usuarioId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        inicializarComponentes();
        IniciarComponentes();

        botao_para_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome = edit_nome.getText().toString();
                String email = edit_email.getText().toString();
                String senha = edit_senha.getText().toString();

                if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()){
                    Snackbar snackbar = Snackbar.make(v, mensagens[0], Snackbar.LENGTH_LONG);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();

                 } else {
                    CadastrarUsuario(v);
                        }
            }
        });




        bt_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrar();
            }
        });

        getSupportActionBar().hide();
    }


    private void CadastrarUsuario(View v){
        String email = edit_email.getText().toString();
        String senha = edit_senha.getText().toString();

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override

            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    SalvarDadosUsuario();

                    Snackbar snackbar = Snackbar.make(v, mensagens[1], Snackbar.LENGTH_LONG);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();

                } else{
                    String erro;
                    try {
                        throw task.getException();

                    }catch (FirebaseAuthWeakPasswordException e) {
                        erro = "Digite uma senha com no minimo 6 caracteres";
                    }catch (FirebaseAuthUserCollisionException e) {
                        erro = "Conta já cadastrada";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        erro = "E-mail invalido ";

                    }catch (Exception e){
                        erro = "Erro ao cadastrar o Usuário";
                    }
                    Snackbar snackbar = Snackbar.make(v,erro, Snackbar.LENGTH_LONG);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();

                }
            }
        });
    }


    private void SalvarDadosUsuario(){
        String nome = edit_nome.getText().toString();

        FirebaseFirestore banco_dados = FirebaseFirestore.getInstance();

        Map <String,Object> usuarios = new HashMap<>();
        usuarios.put("nome",nome);

        usuarioId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = banco_dados.collection("Usuarios").document(usuarioId);

        documentReference.set(usuarios).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d("banco_dados", "Sucesso ao salvar os dados");

            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("bancoD_erro", "Erro ao salvar os dados" + e.toString());

                    }
                });

    }

    private void  IniciarComponentes(){
        edit_nome = findViewById(R.id.edit_nome);
        edit_email = findViewById(R.id.edit_email);
        edit_senha = findViewById(R.id.edit_senha);
        botao_para_cadastrar = findViewById(R.id.botao_para_cadastrar);

    }

    private void inicializarComponentes() {
        this.bt_voltar = findViewById(R.id.bt_voltar);
    }

    private void cadastrar() {
        // Banco de dados,cadstro

        Intent intent2 = new  Intent(cadastro.this,LoginN.class);
        startActivity(intent2);
    }


}