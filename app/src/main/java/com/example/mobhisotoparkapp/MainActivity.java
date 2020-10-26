package com.example.mobhisotoparkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnAracGiris, btnAracCikis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAracGiris=findViewById(R.id.btn_AracGiris);
        btnAracCikis=findViewById(R.id.btn_AracCikis);


        btnAracGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent giris=new Intent(MainActivity.this, AracGirisActivity.class);
                startActivity(giris);
            }
        });

        btnAracCikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cikis=new Intent(MainActivity.this, AracCikisActivity.class);
                startActivity(cikis);
            }
        });
    }
}
