package com.farhan.sqllite;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BuatBiodata extends AppCompatActivity {

    DataHelper dbHelper;
    EditText text1, text2, text3, text4, text5;
    Button btnSimpan, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_biodata);

        dbHelper = new DataHelper(this);
        text1 = findViewById(R.id.editText1);
        text2 = findViewById(R.id.editText2);
        text3 = findViewById(R.id.editText3);
        text4 = findViewById(R.id.editText4);
        text5 = findViewById(R.id.editText5);

        btnSimpan = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelInsert();
            }
        });
    }

    public void insertData() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            db.execSQL(String.format("INSERT INTO biodata (no, nama, tgl, jk, alamat) VALUES ('%s', '%s', '%s', '%s', '%s')",
                    text1.getText().toString(),
                    text2.getText().toString(),
                    text3.getText().toString(),
                    text4.getText().toString(),
                    text5.getText().toString()
            ));
            Toast.makeText(getApplicationContext(), "Data berhasil disave", Toast.LENGTH_LONG).show();
            MainActivity.mainActivity.refreshList();
            finish();
        } catch (SQLException e) {
            Toast.makeText(getApplicationContext(), "Data gagal disave: " + e.getMessage(), Toast.LENGTH_LONG).show();
            MainActivity.mainActivity.refreshList();
            finish();
        }
    }

    public void cancelInsert() {
        finish();
    }
}