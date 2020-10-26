package com.example.mobhisotoparkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class AracCikisActivity extends AppCompatActivity {
    EditText etCikisPlaka;
    CheckBox cbTumSonuclar;
    TextView tvPlakalar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arac_cikis);
        etCikisPlaka=findViewById(R.id.et_CikisPlaka);
        cbTumSonuclar=findViewById(R.id.cb_TumSonuclar);
        tvPlakalar=findViewById(R.id.tv_Plakalar);


        tvPlakalar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent plaka=new Intent(AracCikisActivity.this, PlakaDetayActivity.class);
                startActivity(plaka);
            }
        });
    }
}
