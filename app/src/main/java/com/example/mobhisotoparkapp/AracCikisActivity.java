package com.example.mobhisotoparkapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AracCikisActivity extends AppCompatActivity {

   // EditText etCikisPlaka;
    //Button btnAra;
    ListView lvPlakalar;
    //long gecenSure;
   // Double ucret;
    PlakaModel pl;
   // Calendar calendar;
    //ArrayList<String> plakaList;
    //private static  Date cikisSaati= new Date();
    //private static  Date date= new Date();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arac_cikis);
        lvPlakalar=findViewById(R.id.lv_Plakalar);
      //  btnAra=findViewById(R.id.btn_Ara);
      //  etCikisPlaka=findViewById(R.id.et_CikisPlaka);



      /*  btnAra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String arananPlaka=etCikisPlaka.getText().toString();
                DatabaseHelper databaseHelper=new DatabaseHelper(AracCikisActivity.this);
                databaseHelper.searchPlaka(arananPlaka);
                Intent intent=new Intent(AracCikisActivity.this,PlakaDetayActivity.class);
                startActivity(intent);

            }
        });
*/

        DatabaseHelper databaseHelper = new DatabaseHelper(AracCikisActivity.this);
        final List<PlakaModel> everyone = databaseHelper.getEveryone();

        final ArrayAdapter plakaArrayAdapter=new ArrayAdapter<PlakaModel>(AracCikisActivity.this,android.R.layout.simple_expandable_list_item_1,everyone);
        lvPlakalar.setAdapter(plakaArrayAdapter);



        lvPlakalar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                pl= (PlakaModel) plakaArrayAdapter.getItem(position);

                SimpleDateFormat df=new SimpleDateFormat("hh:mm:ss"); //butona bastıgımdaki saati aliyorum
                Date cikisSaati= new Date();



                /* final String giris=pl.getGIRIS_SAATI();

                SimpleDateFormat df=new SimpleDateFormat("hh:mm:ss"); //butona bastıgımdaki saati aliyorum
                Date cikisSaati= new Date();

                DateFormat format=new SimpleDateFormat("hh:mm:ss");

                try{
                    date=format.parse(giris);
                }catch (ParseException e){
                    e.printStackTrace();
                }*/



               /* Long giriszamani=getTimeStamp2();
                Long cikiszamani=getTimeStamp();
                Long gecensure=getGecenSure(cikiszamani,giriszamani);
                Double sure=Double.valueOf(gecensure);
                Date toplamSure=new Date();


               // ucret=ucretHesaplama(sure);*/

                Intent intent=new Intent(AracCikisActivity.this,PlakaDetayActivity.class);
                intent.putExtra("plakaId",Integer.toString(pl.getPLAKA_ID()));
                intent.putExtra("plaka",pl.getPLAKA());
                intent.putExtra("girisSaati",pl.getGIRIS_SAATI());
                intent.putExtra("cikisSaati",df.format(cikisSaati));
               // intent.putExtra("gecenSure",Double.toString(sure));
                //intent.putExtra("ucret",ucret);

                startActivity(intent);



            }
        });

    }

   /* private double ucretHesaplama(Double sure){
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


    public String getTime(){
        calendar.setTime(this.cikisSaati);
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
        return this.cikisSaati.getTime();
    }
    public String getTime2(){
        calendar.setTime(this.date);
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
        return this.date.getTime();
    }

    public Long getGecenSure(Long cikis,Long giris){
        Long fark = ((cikis-giris)/1000/60);

        return fark;

    }










   /* public static long getDiff(Long giris,Long cikis){
        long timeDiffInMillis=cikis.getTimeInMillis()-giris.getTimeInMillis();
         return timeDiffInMillis/MILLIS_IN_MIN;
    }*/








   /* @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        MenuItem searchItem=menu.findItem(R.id.item_search);
        SearchView searchView =(SearchView) MenuItemCompat.getActionView(searchItem);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {


                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }


    */








/*    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        MenuItem searchItem=menu.findItem(R.id.item_search);
        SearchView searchView =(SearchView) MenuItemCompat.getActionView(searchItem);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<String> plakaList=new ArrayList<>();
                PlakaModel pl;
                String arananPlaka="";
                DatabaseHelper databaseHelper = new DatabaseHelper(AracCikisActivity.this);
                ArrayList<String> plakaListesi=new ArrayList<>();

                for(arananPlaka  ){
                    if(arananPlaka.toLowerCase().contains(newText.toLowerCase())){
                        plakaListesi.add(arananPlaka);
                    }
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(AracCikisActivity.this,android.R.layout.simple_list_item_1,plakaListesi);
                lvPlakalar.setAdapter(adapter);

                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
*/


}
