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

public class EdukasiActivity extends AppCompatActivity {
    private RecyclerView rvBooklist;
    private RecyclerView.Adapter booklistAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private DatabaseReference mDatabase;
    ArrayList<Book> list;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edukasi);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("education");
        list=new ArrayList<>();
        rvBooklist = findViewById(R.id.rv);

        getdata();
    }
    private void getdata() {

        // calling add value event listener method
        // for getting the values from database.
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot ds : snapshot.getChildren()) {
//                    Log.d("TAG",ds.getValue(String.class));
                    Book book=ds.getValue(Book.class);
                    Log.d("TAGG","oke"+book.getType());
//                    if(book.getType().equals("adventure")){
                    list.add(book);
//                    }
//                    if(.equals("adventure")){
//                        list.add(book);
//                    }
                    rvBooklist.setHasFixedSize(true);

                    layoutManager = new LinearLayoutManager(EdukasiActivity.this);
                    booklistAdapter = new BooklistAdapter(list);

                    rvBooklist.setLayoutManager(layoutManager);
                    rvBooklist.setAdapter(booklistAdapter);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
                Toast.makeText(EdukasiActivity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }

}