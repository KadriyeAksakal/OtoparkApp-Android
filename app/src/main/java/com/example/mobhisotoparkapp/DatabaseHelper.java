package com.example.mobhisotoparkapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


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



    public void deletePlaka(int id){
        database=this.getWritableDatabase();
        database.delete(TABLE_NAME,COL_ID+" = ? ",new String[]{String.valueOf(id)});
    }

    public List<PlakaModel> getEveryone(){
        List<PlakaModel> returnList=new ArrayList<>();

        //veritabnından verileri getiriyorum
        String queryString= "SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(queryString,null);

        if (cursor.moveToFirst()){
            do{
                int plakaID=Integer.parseInt(cursor.getString(0));  //id'nin index numarasını alıyorum
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


    public List<PlakaModel> search(String keyword) {
        List<PlakaModel> plakalar = null;
        try {
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT PLAKA FROM " + TABLE_NAME + " WHERE " + COL_NAME + " like ?", new String[] { "%" + keyword + "%" });
            if (cursor.moveToFirst()) {
                plakalar = new ArrayList<PlakaModel>();
                do {
                    PlakaModel plaka = new PlakaModel();
                    plaka.setPLAKA_ID(cursor.getInt(0));
                    plaka.setPLAKA(cursor.getString(1));
                    plaka.setGIRIS_SAATI(cursor.getString(2));
                    plakalar.add(plaka);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            plakalar = null;
        }
        return plakalar;
    }


    //Plaka arama
  /*  public void searchPlaka(String arananplaka){
        List<PlakaModel> plakaList=new ArrayList<>();
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor cursor =database.rawQuery("SELECT PLAKA FROM OTOPARK WHERE PLAKA LIKE '"+arananplaka+"' ",null);
        if(cursor != null){
            if(cursor.moveToFirst()){
                do{
                    String plaka=cursor.getString(1);
                    PlakaModel newPlaka = new PlakaModel(plaka);
                    plakaList.add(newPlaka);

                }while(cursor.moveToNext());

            }else {}

        }cursor.close();
        database.close();
    }*/





    //Benzer verileri de listelemek için
  /*  public Cursor searchPlaka(String text){
        SQLiteDatabase db=this.getReadableDatabase();
        String query="SELECT * FROM "+TABLE_NAME+" WHERE "+COL_NAME+" LIKE '%"+text+"%'";
        Cursor cursor=db.rawQuery(query,null);
        return cursor;
    }*/

}
