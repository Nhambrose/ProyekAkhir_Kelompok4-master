package com.example.proyekakhir_kelompok4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

public class BookList extends Activity {
    ImageView iv_anime, iv_horror, iv_adventure, iv_education;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        iv_anime = findViewById(R.id.anime);
        iv_horror = findViewById(R.id.horror);
        iv_adventure = findViewById(R.id.adventure);
        iv_education = findViewById(R.id.education);

        iv_anime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentanime = new Intent(BookList.this, DaftarBuku.class);
                startActivity(intentanime);
            }
        });
        iv_horror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(BookList.this,HorrorActivity.class);
                startActivity(intent);
            }
        });
        iv_adventure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(BookList.this,AdventureActivity.class);
                startActivity(intent);
            }
        });
        iv_education.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(BookList.this,EdukasiActivity.class);
                startActivity(intent);
            }
        });
    }
}
