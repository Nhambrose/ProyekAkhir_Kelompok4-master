package com.example.proyekakhir_kelompok4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imgLogout, imgProfile, imgList, imgHistory, imgKontak, imgTentang;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private DatabaseReference reference;

    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        imgLogout = (ImageView) findViewById(R.id.logout);
        imgProfile = (ImageView) findViewById(R.id.profile);
        imgList = (ImageView) findViewById(R.id.listbuku);
//        imgHistory = (ImageView) findViewById(R.id.history);
        imgKontak = (ImageView) findViewById(R.id.kontak);
        imgTentang = (ImageView) findViewById(R.id.tentang);
        imgLogout.setOnClickListener(this);

        imgProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        imgList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentList = new Intent(HomeActivity.this, BookList.class);
                startActivity(intentList);
            }
        });

//        imgHistory.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent IntentHistory = new Intent(HomeActivity.this, History.class);
//                startActivity(IntentHistory);
//            }
//        });

        imgKontak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intetkontak = new Intent(HomeActivity.this, ContactUs.class);
                startActivity(intetkontak);
            }
        });

        imgTentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intenttentang = new Intent(HomeActivity.this, Tentang.class);
                startActivity(intenttentang);
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("dataRegister");
        userID = user.getUid();

        final TextView tvUsername = (TextView) findViewById(R.id.textUsername);
        Toast.makeText(HomeActivity.this,getIntent().getStringExtra("method"),Toast.LENGTH_SHORT).show();
        if(getIntent().getStringExtra("method")=="google"){
            tvUsername.setText(user.getDisplayName());
        }else{
            reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    DataRegister dataRegister = snapshot.getValue(DataRegister.class);

                    if (dataRegister != null) {
                        String usr = dataRegister.username;
                        tvUsername.setText(usr);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(HomeActivity.this, "Something wrong happened!", Toast.LENGTH_LONG).show();
                }
            });
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logout:
                logOut();
                break;
        }
    }

    public void logOut(){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);//makesure user cant go back
        startActivity(intent);
    }
}