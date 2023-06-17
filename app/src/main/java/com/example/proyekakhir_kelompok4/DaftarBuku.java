package com.example.proyekakhir_kelompok4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DaftarBuku extends AppCompatActivity {

    private RecyclerView rvBooklist;
    private RecyclerView.Adapter booklistAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private DatabaseReference mDatabase;
    ArrayList<Book> list;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_buku);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("anime");
        list=new ArrayList<>();
        rvBooklist = findViewById(R.id.rv);

        getdata();
    }
    private void getdata() {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()) {
                    Book book=ds.getValue(Book.class);
                    list.add(book);
                    rvBooklist.setHasFixedSize(true);
                    layoutManager = new LinearLayoutManager(DaftarBuku.this);
                    booklistAdapter = new BooklistAdapter(list);
                    rvBooklist.setLayoutManager(layoutManager);
                    rvBooklist.setAdapter(booklistAdapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(DaftarBuku.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}