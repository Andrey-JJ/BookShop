package ru.mivlgu.bookshop.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.mivlgu.bookshop.R;
import ru.mivlgu.bookshop.models.BookingShop;
import ru.mivlgu.bookshop.utils.Utils;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {
    private List<BookingShop> bookings = new ArrayList<>();

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_booking_item, parent, false);
        return new BookingViewHolder(view);
    }

    @Override
    public int getItemCount() { return bookings.size(); }

    public void setData(List<BookingShop> newList) {
        bookings.clear();
        bookings.addAll(newList);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position){
        BookingShop booking = bookings.get(position);
        int count = Collections.frequency(bookings, booking.getBook());
        if(booking.getBook().getImage() != ""){
            holder.bookImage.setImageResource(Utils.getImageFromResourse(booking.getBook().getImage()));
        }
        else {
            holder.bookImage.setImageResource(R.drawable.placeholder_image);
        }
        holder.position.setText(String.format("%s",booking.getId()));
        holder.bookTitle.setText(booking.getBook().getTitle());
        holder.bookCount.setText("Удалить из корзины");
    }

    static class BookingViewHolder extends RecyclerView.ViewHolder{
        TextView position;
        TextView bookTitle;
        ImageView bookImage;
        Button bookCount;
        public BookingViewHolder(@NonNull View itemView){
            super(itemView);
            position = itemView.findViewById(R.id.textView_rv_booking_pos);
            bookTitle = itemView.findViewById(R.id.textView_rv_booking_title);
            bookImage = itemView.findViewById(R.id.imgView_rv_booking_image);
            bookCount = itemView.findViewById(R.id.btn_rv_booking_del);
        }
    }
}
