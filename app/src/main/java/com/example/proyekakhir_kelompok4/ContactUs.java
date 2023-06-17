package com.example.proyekakhir_kelompok4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ContactUs extends AppCompatActivity {

    Button fb, instagram, email, whatsapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        fb = findViewById(R.id.btnFb);
        instagram = findViewById(R.id.btnIg);
        email = findViewById(R.id.btnEmail);
        whatsapp = findViewById(R.id.btnWa);

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.facebook.com/nafihelmi.aldianto?mibextid=LQQJ4d";
                Intent intentfacebook = new Intent(Intent.ACTION_VIEW);
                intentfacebook.setData(Uri.parse(url));
                startActivity(intentfacebook);
            }
        });

        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String urlig = "https://instagram.com/nhambrose?igshid=OGQ5ZDc2ODk2ZA==";
                Intent intentig = new Intent(Intent.ACTION_VIEW);
                intentig.setData(Uri.parse(urlig));
                startActivity(intentig);
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentemail = new Intent(Intent.ACTION_SEND);
                intentemail.setType("Text/Html");
                intentemail.putExtra(Intent.EXTRA_EMAIL, new String[]{"nafihelmi@gmail.com"});
                intentemail.putExtra(Intent.EXTRA_SUBJECT, "Buku");
                intentemail.putExtra(Intent.EXTRA_TEXT,"Jika ingin order maupun bertanya-tanya terlebih dahulu silakan isi di sini.");
                startActivity(Intent.createChooser(intentemail,"Send Email"));
            }
        });

        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kirimwa = "Halo, saya ingin order/tanya2 soal buku nih!";
                String nomor = "6287700135248";
                Intent intentwa = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format("https://api.whatsapp.com/send?phone=%s&text=%s", nomor, kirimwa)));
                startActivity(intentwa);
            }
        });

    }
}