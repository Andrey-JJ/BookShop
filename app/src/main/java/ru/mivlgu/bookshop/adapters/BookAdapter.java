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
import java.util.List;

import ru.mivlgu.bookshop.R;
import ru.mivlgu.bookshop.models.BookShop;
import ru.mivlgu.bookshop.utils.Utils;
import ru.mivlgu.bookshop.utils.OnBookItemClickListener;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private List<BookShop> books = new ArrayList<>();

    private List<BookShop> filtred_books = new ArrayList<>();

    private OnBookItemClickListener onBookItemClickListener;

    public void setOnBookItemClickListener(OnBookItemClickListener listener) {
        this.onBookItemClickListener = listener;
    }

    public BookAdapter(){}

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
        filtred_books.clear();
        books.addAll(newList);
        filtred_books = new ArrayList<>(newList);
        notifyDataSetChanged();
    }

    public void filterByAuthor(String author){
        filtred_books.clear();
        for (BookShop book : books) {
            // Предполагая, что у BookShop есть метод getAuthors(), который возвращает список авторов
            String bookAuthor = book.getAuthor().getLastname() + " " + book.getAuthor().getFirstname() + " " + book.getAuthor().getMidname();
            if (bookAuthor.equals(author)) {
                filtred_books.add(book);
            }
        }
        notifyDataSetChanged();
    }

    public void filterByPublisher(String publisher){
        filtred_books.clear();
        for (BookShop book : books) {
            if (book.getPublisher().equals(publisher)) {
                filtred_books.add(book);
            }
        }
        notifyDataSetChanged();
    }

    public void filterByCategory(String category){
        filtred_books.clear();
        for (BookShop book : books) {
            if (book.getCategory().equals(category)) {
                filtred_books.add(book);
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position){
        BookShop book = books.get(position);
        holder.bookTitle.setText(book.getTitle().length() > 25 ? book.getTitle().substring(0,25) + "..." : book.getTitle());
        holder.bookAuthor.setText(book.getAuthor().toString());
        if(book.getImage() != ""){
            holder.bookImage.setImageResource(Utils.getImageFromResourse(book.getImage()));
        }
        else{
            // Если изображение не найдено, можно установить заглушку или другое действие
            holder.bookImage.setImageResource(R.drawable.placeholder_image);
        }
        holder.bookPrice.setText(String.format("Цена: %s", book.getPrice()));

        holder.bookImage.setOnClickListener(v -> {
            if(onBookItemClickListener != null)
                onBookItemClickListener.onBookItemClick(book);
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
