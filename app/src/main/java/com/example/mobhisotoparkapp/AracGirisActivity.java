package com.example.mobhisotoparkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;


import java.text.DateFormat;

public class AracGirisActivity extends AppCompatActivity {
    EditText etGirisPlaka;
    Button btnGirisOnay;
    Context context=this;
    DatabaseHelper databaseHelper= new DatabaseHelper(context);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arac_giris);
        etGirisPlaka=findViewById(R.id.et_GirisPlaka);
        btnGirisOnay=findViewById(R.id.btn_GirisOnay);


        btnGirisOnay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SimpleDateFormat df=new SimpleDateFormat("hh:mm:ss");
                Date girisSaati= new Date();
                PlakaModel plakaModel=new PlakaModel(-1,etGirisPlaka.getText().toString(),df.format(girisSaati));
                Toast.makeText(AracGirisActivity.this,plakaModel.toString(),Toast.LENGTH_LONG).show();
                databaseHelper.addPlaka(plakaModel);
            }
        });
    }

}
