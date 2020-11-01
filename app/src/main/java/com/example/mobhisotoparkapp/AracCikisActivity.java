package com.example.mobhisotoparkapp;

import android.app.SearchManager;
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
//import android.widget.SearchView;

import androidx.appcompat.widget.SearchView;
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

    ListView lvPlakalar;
    PlakaModel pl;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arac_cikis);
        lvPlakalar=findViewById(R.id.lv_Plakalar);



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

                Intent intent=new Intent(AracCikisActivity.this,PlakaDetayActivity.class);
                intent.putExtra("plakaId: ",Integer.toString(pl.getPLAKA_ID()));
                intent.putExtra("plaka: ",pl.getPLAKA());
                intent.putExtra("girisSaati: ",pl.getGIRIS_SAATI());
                intent.putExtra("cikisSaati: ",df.format(cikisSaati));

                startActivity(intent);



            }
        });

    }


   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.item_search).getActionView();
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchPlaka(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchPlaka(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void searchPlaka(String keyword) {
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        List<PlakaModel> plakalar = databaseHelper.search(keyword);
        if (plakalar != null) {
            CustomAdapter customAdapter=new CustomAdapter(AracCikisActivity.this,plakalar);
            lvPlakalar.setAdapter(customAdapter);
        }
    }*/

}

