package com.example.mobhisotoparkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PlakaDetayActivity extends AppCompatActivity {
    TextView tvPlaka, tvGirisSaati, tvCikisSaati, tvGecenSure, tvUcret;
    Button btnIslemBitir;
    String id;
    Double ucret;
    String plaka,girisSaati,cikisSaati,gecenSure;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plaka_detay);
        tvPlaka=findViewById(R.id.tv_Plaka);
        tvGirisSaati=findViewById(R.id.tv_GirisSaati);
        tvCikisSaati=findViewById(R.id.tv_CikisSaati);
        tvGecenSure=findViewById(R.id.tv_GecenSure);
        tvUcret=findViewById(R.id.tv_Ucret);
        btnIslemBitir=findViewById(R.id.btn_IslemBitir);

        Intent intent=getIntent();
        if(intent != null){
             id=intent.getStringExtra("plakaId");
             plaka=intent.getStringExtra("plaka");
             girisSaati=intent.getStringExtra("girisSaati");
             cikisSaati=intent.getStringExtra("cikisSaati");
             gecenSure=intent.getStringExtra("gecenSure");
             ucret=intent.getExtras().getDouble("ucret");
        }
        String getUcret=ucret.toString();
        final int getId=Integer.valueOf(id);
        tvPlaka.setText(plaka);
        tvGirisSaati.setText(girisSaati);
        tvCikisSaati.setText(cikisSaati);
        tvGecenSure.setText(gecenSure);
        tvUcret.setText(getUcret);




        btnIslemBitir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper=new DatabaseHelper(PlakaDetayActivity.this);
                databaseHelper.deletePlaka(getId);
                if(getId > -1){
                    Toast.makeText(PlakaDetayActivity.this, "Plaka çıkış işlemi gerçekleştirildi.",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(PlakaDetayActivity.this,"Plaka çıkış işlemi başarısız.",Toast.LENGTH_SHORT).show();
                }
                Intent intent=new Intent(PlakaDetayActivity.this,AracCikisActivity.class);
                startActivity(intent);

            }
        });

    }


}
