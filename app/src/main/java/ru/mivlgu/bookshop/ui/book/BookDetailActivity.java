package ru.mivlgu.bookshop.ui.book;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.io.Serializable;

import ru.mivlgu.bookshop.R;
import ru.mivlgu.bookshop.models.BookShop;
import ru.mivlgu.bookshop.utils.Utils;

public class BookDetailActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        Intent intent = getIntent();
        if(intent != null && intent.hasExtra("book")){
            BookShop book = intent.getParcelableExtra("book");

            ImageView bookImg = findViewById(R.id.book_image);
            TextView bookTitle = findViewById(R.id.book_title);
            TextView bookAuthor = findViewById(R.id.book_author);
            TextView bookPublisher = findViewById(R.id.book_publisher);
            TextView bookVolume = findViewById(R.id.book_volume);
            TextView bookDescription = findViewById(R.id.book_description);
            Button cartBtn = findViewById(R.id.btn_add_to_cart);
            Button buyBtn = findViewById(R.id.btn_buy);

            if(book.getImage() != ""){
                bookImg.setImageResource(Utils.getImageFromResourse(book.getImage()));
            }
            else {
                bookImg.setImageResource(R.drawable.placeholder_image);
            }

            bookTitle.setText(book.getTitle());
            bookAuthor.setText(String.format("Автор: %s", book.getAuthor().toString()));
            bookPublisher.setText(String.format("Издатель: %s | %s", book.getPublisher().getName(), book.getPublication_year()));
            bookVolume.setText(String.format("Кол-во страниц: %s", book.getVolume()));
            bookDescription.setText(book.getDescription());

            buyBtn.setText(String.format("Купить за %s",book.getPrice()));
        }
    }
}

