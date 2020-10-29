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
    EditText etCikisPlaka;
    Button btnTumSonuclar;
    ListView lvPlakalar;
    Button btnAra;
    List<PlakaModel> plakalar;
    String gecenSure="d4fd4f";
    Double ucret=0.0;

    PlakaModel pl;
    DatabaseHelper databaseHelper;
    CustomAdapter customAdapter;
    ArrayList<PlakaModel> arrayList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arac_cikis);
        lvPlakalar=findViewById(R.id.lv_Plakalar);





        /*btnAra.setOnClickListener(new View.OnClickListener() {
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
                //Intent detayintent=new Intent(getApplicationContext(),PlakaDetayActivity.class);
                //detayintent.putExtra("id",plakalar.get(position).getPLAKA_ID());
                //startActivityForResult(detayintent,1);
                // pl=customAdapter.getItem(position);
                SimpleDateFormat df=new SimpleDateFormat("hh:mm:ss"); //butona bastıgımdaki saati aliyorum
                Date cikisSaati= new Date();
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

    /*private double ucretHesapla(Date girisSaati, Date cikisSaati){
        double ucret;
        String
        ucret=girisSaati-cikisSaati;
        return ucret;
    }
*/



}
