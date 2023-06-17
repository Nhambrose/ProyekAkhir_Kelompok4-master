package com.example.proyekakhir_kelompok4;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BooklistAdapter extends RecyclerView.Adapter<BooklistAdapter.BooklistViewHolder> {

    private ArrayList<Book> book_list;

    public static  class BooklistViewHolder extends RecyclerView.ViewHolder
    {

        public ImageView movie_images;
        public ImageView movie_rating;
        public TextView movie_title;
        public TextView movie_desc;
        public TextView movie_price;
        public CardView card;

        public BooklistViewHolder(View itemView)
        {
            super(itemView);

            movie_images = itemView.findViewById(R.id.foto);
            movie_rating = itemView.findViewById(R.id.rating1);
            movie_title = itemView.findViewById(R.id.tv_title);
            movie_desc = itemView.findViewById(R.id.tv_subtitle);
            movie_price = itemView.findViewById(R.id.price);
            card=itemView.findViewById(R.id.card);

        }
    }

    public BooklistAdapter(ArrayList<Book> booklist)
    {
        book_list = booklist;
    }

    @NonNull
    @Override
    public BooklistViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_book_list, parent, false);
        BooklistViewHolder booklistViewHolder = new BooklistViewHolder(view);

        return booklistViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BooklistViewHolder holder, int position) {

        Book newItems = book_list.get(position);

        Picasso.get().load(newItems.getGambar()).into(holder.movie_images);
        if (newItems.getRating()==1){
//            Picasso.get().load()
            holder.movie_rating.setImageResource(R.drawable.rating1);
        }else if(newItems.getRating()==2){
            holder.movie_rating.setImageResource(R.drawable.rating2);
        }else if (newItems.getRating()==3){
            holder.movie_rating.setImageResource(R.drawable.rating3);
        }else if (newItems.getRating()==4){
            holder.movie_rating.setImageResource(R.drawable.rating4);
        }else if (newItems.getRating()==5){
            holder.movie_rating.setImageResource(R.drawable.rating);
        }

        holder.movie_title.setText(newItems.getNama());
        holder.movie_desc.setText(newItems.getDeskripsi());
        holder.movie_price.setText(newItems.getHarga());
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(holder.itemView.getContext())
                        .setTitle("Tertarik")
                        .setMessage("Silahkan tekan ya untuk menghubungi kami!!")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton("Hubungi", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent= new Intent(holder.itemView.getContext(), ContactUs.class);
                                holder.itemView.getContext().startActivity(intent);
                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return book_list.size();
    }
}