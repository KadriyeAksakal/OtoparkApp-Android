package com.example.mobhisotoparkapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public final static String DATABASE_NAME = "OTOPARK.DB"; //Veritabani olusturuldu
    public final static String TABLE_NAME = "OTOPARK";        //Tablo olusturuldu
    public final static String COL_ID = "PLAKA_ID";
    public final static String COL_NAME = "PLAKA";
    public final static String COL_TIME = "GIRIS_SAATI";

    SQLiteDatabase database;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_QUERY = " CREATE TABLE " + TABLE_NAME + "(PLAKA_ID INTEGER PRIMARY KEY AUTOINCREMENT,"+COL_NAME+" TEXT NOT NULL,"+COL_TIME+" TIME NOT NULL)";
        db.execSQL(CREATE_TABLE_QUERY);
        this.database = db;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String UPGRADE_QUERY = " DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(UPGRADE_QUERY);
        this.onCreate(db);
    }

    public boolean addPlaka(PlakaModel plaka) {
        database=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COL_NAME,plaka.getPLAKA());
        values.put(COL_TIME,plaka.getGIRIS_SAATI());

        Long result =database.insert(TABLE_NAME,null,values);
        database.close();
        if(result != -1){
            return true;
        }
        else
            return false;
    }

    public Integer deletePlaka(String id){
        database=getWritableDatabase();
        /*String DELETE_QUERY="DELETE FROM " + TABLE_NAME +"WHERE NAME '"+id+"' ";
        database.execSQL(DELETE_QUERY);
        return true;*/
        return database.delete(TABLE_NAME,"ID=?",new String[]{id});

    }

    public List<PlakaModel> getEveryone(){
        List<PlakaModel> returnList=new ArrayList<>();

        //veritabnından verileri getiriyorum
        String queryString= "SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(queryString,null);

        if (cursor.moveToFirst()){
            do{
                int plakaID=cursor.getInt(0);  //id'nin index numarasını alıyorum
                String plaka=cursor.getString(1);
                String girisSaati=cursor.getString(2);
                PlakaModel newPlaka = new PlakaModel(plakaID,plaka,girisSaati);
                returnList.add(newPlaka);

            }while (cursor.moveToNext());
        }else{//hiçbir şey eklenmediyse

        }
        cursor.close();
        db.close();
        return returnList;

    }

}
