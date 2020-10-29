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
               // String plaka=etGirisPlaka.getText().toString();
               // long girisSaati = 0;
               // girisSaati = System.currentTimeMillis();
               // String giris= String.valueOf(girisSaati);
               // System.out.println(giris);
               // DateFormat dateFormatTime = android.text.format.DateFormat.getTimeFormat(context);
               // String timeStr = dateFormatTime.format(date); android.text.format.DateFormat.getTimeFormat(context);
                SimpleDateFormat df=new SimpleDateFormat("hh:mm:ss");
                Date girisSaati= new Date();
                PlakaModel plakaModel=new PlakaModel(-1,etGirisPlaka.getText().toString(),df.format(girisSaati));
                Toast.makeText(AracGirisActivity.this,plakaModel.toString(),Toast.LENGTH_LONG).show();
                databaseHelper.addPlaka(plakaModel);

              /*  if(!TextUtils.isEmpty(plaka)){
                    if(databaseHelper.addPlaka(new Plaka(plaka,giris))){
                        Toast.makeText(AracGirisActivity.this, "Plaka başarı ile kaydedildi.", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(AracGirisActivity.this, "Plaka kaydedilemedi.", Toast.LENGTH_SHORT).show();
                    }

                }*/
            }
        });
    }

}
