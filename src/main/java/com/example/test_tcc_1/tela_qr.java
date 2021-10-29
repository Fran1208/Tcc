package com.example.test_tcc_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import android.content.ClipData;
import android.content.ClipboardManager;

public class tela_qr extends AppCompatActivity {

    private EditText qrValor;
    private Button bt_gerar_codigo;
    private ImageView imagem_qr;
    private Button bt_gerar_link;
    String[] mensagens = {"Preencha o campo indicado","link copiado com sucesso"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_qr);

        qrValor = findViewById(R.id.valor_qr);
        bt_gerar_codigo = findViewById(R.id.bt_gerar_codigo);
        imagem_qr = findViewById(R.id.imagem_qr);
        bt_gerar_link = findViewById(R.id.bt_gerar_link);

            bt_gerar_codigo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String valor = qrValor.getText().toString();

                    if (valor.isEmpty()){
                        Snackbar snackbar = Snackbar.make(v, mensagens[0], Snackbar.LENGTH_LONG);
                        snackbar.setBackgroundTint(Color.WHITE);
                        snackbar.setTextColor(Color.BLACK);
                        snackbar.show();

                    }else {

                        QRGEncoder qrgEncoder = new QRGEncoder(valor,null, QRGContents.Type.TEXT,350);
                        Bitmap qrBits =qrgEncoder.getBitmap();
                        imagem_qr.setImageBitmap(qrBits);
                    }
                }
            });

            bt_gerar_link.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("label", "00020126360014BR.GOV.BCB.PIX0114+5547991557752520400005303986540589.005802BR5905Edson6008Blumenau62130509APPCINEMA63049818\n" +
                            "\n");
                    clipboard.setPrimaryClip(clip);

                    Snackbar snackbar = Snackbar.make(v, mensagens[1], Snackbar.LENGTH_LONG);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }
            });


    }
}