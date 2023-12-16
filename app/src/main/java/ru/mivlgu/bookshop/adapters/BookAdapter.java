package ru.mivlgu.bookshop.adapters;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ru.mivlgu.bookshop.R;
import ru.mivlgu.bookshop.databinding.FragmentBookDetailBinding;
import ru.mivlgu.bookshop.models.BookShop;
import ru.mivlgu.bookshop.ui.book.BookDetailFragment;
import ru.mivlgu.bookshop.utils.BookShopUtils;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private List<BookShop> books = new ArrayList<>();
    private FragmentManager fragmentManager;

    public BookAdapter(FragmentManager fragmentManager){
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_book_item, parent, false);
        return new BookAdapter.BookViewHolder(view);
    }

    @Override
    public int getItemCount() { return books.size(); }

    public void setData(List<BookShop> newList) {
        books.clear();
        books.addAll(newList);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position){
        BookShop book = books.get(position);
        holder.bookTitle.setText(book.getTitle().length() > 25 ? book.getTitle().substring(0,25) + "..." : book.getTitle());
        holder.bookAuthor.setText(book.getAuthor().toString());
        if(book.getImage() != ""){
            holder.bookImage.setImageResource(BookShopUtils.getImageFromResourse(book.getImage()));
        }
        else{
            // Если изображение не найдено, можно установить заглушку или другое действие
            holder.bookImage.setImageResource(R.drawable.placeholder_image);
        }
        holder.bookPrice.setText(String.format("Цена: %s", book.getPrice()));
        holder.bookPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookDetailFragment fragment = BookDetailFragment.newInstance(book);
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    static class BookViewHolder extends RecyclerView.ViewHolder{
        TextView bookTitle;
        TextView bookAuthor;
        ImageView bookImage;
        Button bookPrice;
        public BookViewHolder(@NonNull View itemView){
            super(itemView);
            bookTitle = itemView.findViewById(R.id.textView_rv_book_title);
            bookAuthor = itemView.findViewById(R.id.textView_rv_book_author);
            bookImage = itemView.findViewById(R.id.imgView_rv_book_image);
            bookPrice = itemView.findViewById(R.id.btnView_rv_book_price);
        }
    }
}
