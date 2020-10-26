package com.example.mobhisotoparkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AracGirisActivity extends AppCompatActivity {
    EditText etGirisPlaka;
    Button btnGirisOnay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arac_giris);
        etGirisPlaka=findViewById(R.id.et_GirisPlaka);
        btnGirisOnay=findViewById(R.id.btn_GirisOnay);
    }
}
