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

public class ListBuku extends AppCompatActivity {

    private RecyclerView rvBooklist;
    private RecyclerView.Adapter booklistAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private DatabaseReference mDatabase;
    ArrayList<Book> list;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_buku);

        ArrayList<ArrayItems> booklist = new ArrayList<>();
        booklist.add(new ArrayItems(R.drawable.jjk, R.drawable.rating, "Jujutsu Kaisen", "Adventure of Spirit", "Rp. 10.000"));
        booklist.add(new ArrayItems(R.drawable.marin, R.drawable.rating, "My DressUp Darling", "Kitagawa Marin's Lovestory", "Rp. 5.000"));
        booklist.add(new ArrayItems(R.drawable.zerotwo, R.drawable.rating, "Darling in Franxx", "Eternal Love", "Rp. 150.000"));

        mDatabase = FirebaseDatabase.getInstance().getReference().child("adventure");
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

                    layoutManager = new LinearLayoutManager(ListBuku.this);
                    booklistAdapter = new BooklistAdapter(list);

                    rvBooklist.setLayoutManager(layoutManager);
                    rvBooklist.setAdapter(booklistAdapter);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
                Toast.makeText(ListBuku.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}