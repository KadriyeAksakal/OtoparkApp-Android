package com.example.mobhisotoparkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AracCikisActivity extends AppCompatActivity {

    ListView lvPlakalar;
    //String gecenSure;
    Double ucret;
    PlakaModel pl;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arac_cikis);
        lvPlakalar=findViewById(R.id.lv_Plakalar);






        /*/btnAra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase database=databaseHelper.getReadableDatabase();
                String arananPlaka=etCikisPlaka.getText().toString();
                Cursor c =database.rawQuery("SELECT PLAKA FROM OTOPARK WHERE PLAKA LIKE '"+arananPlaka+"' ",null);
                if(c != null){
                    if(c.moveToFirst()){
                        do{
                            String plaka=c.getString(c.getColumnIndex("PLAKA"));
                            //Toast.makeText(AracCikisActivity.this,"SONUC: "+ plaka,Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(AracCikisActivity.this, PlakaDetayActivity.class);
                            intent.putExtra("plaka",plaka);
                            startActivity(intent);
                        }while(c.moveToNext());

                    }

                }

            }
        });*/

        DatabaseHelper databaseHelper = new DatabaseHelper(AracCikisActivity.this);
        List<PlakaModel> everyone = databaseHelper.getEveryone();

        final ArrayAdapter plakaArrayAdapter=new ArrayAdapter<PlakaModel>(AracCikisActivity.this,android.R.layout.simple_expandable_list_item_1,everyone);
        lvPlakalar.setAdapter(plakaArrayAdapter);



        lvPlakalar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                pl= (PlakaModel) plakaArrayAdapter.getItem(position);

                SimpleDateFormat df=new SimpleDateFormat("hh:mm:ss"); //butona bastıgımdaki saati aliyorum
                Date cikisSaati= new Date();

                String cikis=String.valueOf(cikisSaati);
                Double cikiszamani=Double.valueOf(cikis);
                Double cikiszamani1=cikiszamani/1000;
                Double giriszamani=Double.valueOf(pl.getGIRIS_SAATI());
                Double giriszamani1=giriszamani/1000;
                Double gecenzaman=0.0;
                gecenzaman=cikiszamani1-giriszamani1;
                String gecenSure=String.valueOf(gecenzaman);
                //String gecenzaman="999.0";
                Double sure=Double.valueOf(gecenzaman);
                ucret=ucretHesaplama(sure);

                Intent intent=new Intent(AracCikisActivity.this,PlakaDetayActivity.class);
                intent.putExtra("plakaId",pl.getPLAKA_ID());
                intent.putExtra("plaka",pl.getPLAKA());
                intent.putExtra("girisSaati",pl.getGIRIS_SAATI());
                intent.putExtra("cikisSaati",df.format(cikisSaati));
                intent.putExtra("gecenSure",gecenSure);
                intent.putExtra("ucret",ucret);

                startActivity(intent);



            }
        });

    }



    private double ucretHesaplama(Double sure){
        double ucretHesap=0.0;

        if(sure <= 900){
            ucretHesap = 0.0;
        }
        else if(sure > 900 && sure < 3600){
            ucretHesap = 1.0;
        }
        else if(sure > 3600 && sure < 7200){
            ucretHesap = 2.0;
        }
        else {
            ucretHesap = 5.0;
        }

        return ucretHesap;
    }




}
