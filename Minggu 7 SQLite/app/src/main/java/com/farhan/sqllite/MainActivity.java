package com.farhan.sqllite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    String[] daftar;
    Button btn;
    ListView listView1;
    protected Cursor cursor;
    DataHelper dbcenter;
    public static MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btnTambah);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BuatBiodata.class);
                startActivity(intent);
            }
        });
        mainActivity = this;
        dbcenter = new DataHelper(this);
        refreshList();
    }

    public void refreshList() {
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM biodata", null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc = 0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(1);
        }
        listView1 = findViewById(R.id.listView1);
        listView1.setAdapter(new ArrayAdapter<>(this, R.layout.gaya_list, daftar));
        listView1.setSelected(true);
        listView1.setOnItemClickListener(this);
        ((ArrayAdapter) listView1.getAdapter()).notifyDataSetInvalidated();
    }

    @Override
    public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
        final String selection = daftar[arg2];
        final CharSequence[] dialogItem = {"Lihat", "Update", "Hapus"};
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Opsi");
        builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                Intent i;
                switch (item) {
                    case 0:
                        i = new Intent(getApplicationContext(), LihatBiodata.class);
                        i.putExtra("nama", selection);
                        startActivity(i);
                        break;
                    case 1:
                        i = new Intent(getApplicationContext(), UpdateBiodata.class);
                        i.putExtra("nama", selection);
                        startActivity(i);
                        break;
                    case 2:
                        SQLiteDatabase db = dbcenter.getWritableDatabase();
                        db.execSQL(String.format("DELETE FROM biodata WHERE nama = '%s'", selection));
                        refreshList();
                        break;
                }
            }
        });
        builder.create().show();
    }
}