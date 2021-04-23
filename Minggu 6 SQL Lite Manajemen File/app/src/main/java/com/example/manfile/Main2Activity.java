package com.example.manfile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.BreakIterator;

public class Main2Activity extends AppCompatActivity {
    TextView showText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        showText = findViewById(R.id.getText);
    }

    public void back(View view) {
        Intent intent = new Intent(Main2Activity.this, MainActivity.class); // FUngsinya untuk Perpindahan Halaman Ke Activity_main.xml ke Activity_main2.xml
        startActivity(intent);
    }

    public void getPublic(View view) {
        File folder = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS); // Folder Name
        File myFile = new File(folder, "myData.txt");  // File Name dan Sama-kan dengan yang di MainActivity.xml
        String text = getdata(myFile);
        if (text != null) {
            showText.setText(text);
        } else {
            showText.setText("Data Tidak Ada");
        }
    }

    public void getPrivate(View view) {
        File folder = getExternalFilesDir("manfile"); // Folder Name Project
        File myFile = new File(folder, "myData2.txt"); // File Name dan Sama-kan dengan yang di MainActivity.xml
        String text = getdata(myFile);
        if (text != null) {
            showText.setText(text);
        } else {
            showText.setText("Data Tidak Ada");
        }
    }

    //    Kegunaan Function ini untuk mengambil isi dari teks yang berasal dari suatu file tertentu
    private String getdata(File myFile) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(myFile);
            int i = -1;
            StringBuilder buffer = new StringBuilder();
            while ((i = fileInputStream.read()) != -1) {
                buffer.append((char) i);
            }
            return buffer.toString();
        } catch (Exception e) {
            e.getMessage();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.getMessage();
                }
            }
        }
        return null;
    }
}