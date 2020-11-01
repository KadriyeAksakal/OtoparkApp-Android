package com.example.mobhisotoparkapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

//extends etme BaseAdapter in bütün özelliklerini kullanabiliyor(hepsini kullanmak zorunda değil)
//implements etme de interface in bütün methodlarını kullanmak zorundadır.

public class CustomAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater; //layoutInflater xml deki nesneyi view olarak gösteriyor.
    private List<PlakaModel> Items;

    TextView tvPlaka,tvGirisSaati,tvCikisSaati,tvGecenSure,tvUcret;

    public CustomAdapter(Activity activity, List Items){
        this.activity=activity;
        this.Items=Items;

    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public PlakaModel getItem(int position) {
        return Items.get(position);
    } //int position nesnenin poziysonunu döndürür.

    @Override
    public long getItemId(int position) {
        return position;
    } //burda da o pozisyonun id'sini long tipinde döndürür.
    //long olmasının sebebi id'ler long olarak tutulur.

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(inflater == null){
            inflater=(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE); //yapılandırıcı
            //getSystemService ile bu sistem sayesinde nesneyi view olarak döndürüyor.
            //alınan plakaList bu sayfadaki veriye esitleniyor.
        }
        if(convertView == null){
            convertView = inflater.inflate(android.R.layout.simple_list_item_1, null);
        }
        tvPlaka=convertView.findViewById(R.id.tv_Plaka);
        tvGirisSaati=convertView.findViewById(R.id.tv_GirisSaati);
        tvCikisSaati=convertView.findViewById(R.id.tv_CikisSaati);
        tvGecenSure=convertView.findViewById(R.id.tv_GecenSure);
        tvUcret=convertView.findViewById(R.id.tv_Ucret);
        return convertView;


    }
}
