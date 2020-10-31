package com.example.mobhisotoparkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class PlakaDetayActivity extends AppCompatActivity {
    TextView tvPlaka, tvGirisSaati, tvCikisSaati, tvGecenSure, tvUcret;
    Button btnIslemBitir;
    String id;
    Double ucret;
    String plaka,girisSaati,cikisSaati,gecenSure;
    DatabaseHelper databaseHelper;
    Calendar calendar;
    public static Date date;
    public static Date date1;

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
            // gecenSure=intent.getStringExtra("gecenSure");
             //ucret=intent.getExtras().getDouble("ucret");
        }

        final int getId=Integer.valueOf(id);
        tvPlaka.setText(plaka);
        tvGirisSaati.setText(girisSaati);
        tvCikisSaati.setText(cikisSaati);





        DateFormat format=new SimpleDateFormat("hh:mm:ss");
        //Date date;

        try{
            date=format.parse(cikisSaati);
        }catch (ParseException e) {
            e.printStackTrace();

        }

        //Date date1;
        try {
            date1=format.parse(girisSaati);
        }catch (ParseException e){
            e.printStackTrace();
        }

        Long giriszamani=getTimeStamp2();
        Long cikiszamani=getTimeStamp();
        Long gecensure=getGecenSure(cikiszamani,giriszamani);
        Double sure=Double.valueOf(gecensure);
        ucret=ucretHesaplama(sure);

        String getUcret=String.valueOf(ucret);
        String getSure=String.valueOf(sure);


        tvUcret.setText(getUcret);
        tvGecenSure.setText(getSure);




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


    public String getTime(){
        calendar.setTime(this.date);
        String cikiszamanisaat=String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
        String cikiszamaniminute=String.valueOf(calendar.get(Calendar.MINUTE));
        if(cikiszamanisaat.length() == 1){
            cikiszamanisaat = "0" + cikiszamanisaat;
        }
        if(cikiszamaniminute.length() == 1){
            cikiszamaniminute = "0" + cikiszamaniminute;
        }
        return cikiszamanisaat + ":" + cikiszamaniminute;
    }

    public Long getTimeStamp(){
        return this.date.getTime();
    }
    public String getTime2(){
        calendar.setTime(date1);
        String giriszamanisaat=String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
        String giriszamaniminute=String.valueOf(calendar.get(Calendar.MINUTE));
        if(giriszamanisaat.length() == 1){
            giriszamanisaat = "0" + giriszamanisaat;
        }
        if(giriszamaniminute.length() == 1){
            giriszamaniminute = "0" + giriszamaniminute;
        }
        return giriszamanisaat + ":" + giriszamaniminute;
    }
    public Long getTimeStamp2(){
        return this.date1.getTime();
    }

    public Long getGecenSure(Long cikis,Long giris){
        Long fark = ((cikis-giris)/1000/60);

        return fark;

    }

    private double ucretHesaplama(Double sure){
        double ucretHesap=0.0;

        if(sure <= 15){
            ucretHesap = 0.0;
        }
        else if(sure > 15 && sure < 60){
            ucretHesap = 1.0;
        }
        else if(sure > 60 && sure < 120){
            ucretHesap = 2.0;
        }
        else {
            ucretHesap = 5.0;
        }

        return ucretHesap;
    }




}
