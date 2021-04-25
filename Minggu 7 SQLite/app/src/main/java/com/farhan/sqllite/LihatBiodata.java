package com.farhan.sqllite;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class LihatBiodata extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    TextView txtNo, txtNama, txtTgl, txtJk, txtAlamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_biodata);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        dbHelper = new DataHelper(this);
        txtNo = findViewById(R.id.txtNomor);
        txtNama = findViewById(R.id.txtNama);
        txtTgl = findViewById(R.id.txtTgl);
        txtJk = findViewById(R.id.txtJk);
        txtAlamat = findViewById(R.id.txtAlamat);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery(String.format("SELECT * FROM biodata WHERE nama = '%s'", getIntent().getStringExtra("nama")), null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            txtNo.setText(cursor.getString(0).toString());
            txtNama.setText(cursor.getString(1).toString());
            txtTgl.setText(cursor.getString(2).toString());
            txtJk.setText(cursor.getString(3).toString());
            txtAlamat.setText(cursor.getString(4).toString());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}