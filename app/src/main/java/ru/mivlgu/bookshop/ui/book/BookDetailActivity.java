package ru.mivlgu.bookshop.ui.book;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Response;
import ru.mivlgu.bookshop.R;
import ru.mivlgu.bookshop.api.ServiceApi;
import ru.mivlgu.bookshop.models.BookShop;
import ru.mivlgu.bookshop.models.BookingShop;
import ru.mivlgu.bookshop.models.Reader;
import ru.mivlgu.bookshop.utils.Utils;

import retrofit2.Callback;

public class BookDetailActivity extends AppCompatActivity {

    BookShop book;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        Intent intent = getIntent();
        if(intent != null && intent.hasExtra("book")){
            book = intent.getParcelableExtra("book");

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

            cartBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addToCart();
                }
            });
        }
    }

    private void addToCart(){
        Reader reader = new Reader(1, "Админов", "Админ", "Админович");
        BookingShop booking = new BookingShop();
        booking.setBook(book);
        booking.setReader(reader);
        ServiceApi.addBooking(booking, new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Успешно добавлено в корзину
                    Toast.makeText(BookDetailActivity.this, "Книга добавлена в корзину", Toast.LENGTH_SHORT).show();
                } else {
                    // Ошибка при добавлении в корзину
                    Toast.makeText(BookDetailActivity.this, "Ошибка при добавлении в корзину", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Ошибка при выполнении запроса
                Toast.makeText(BookDetailActivity.this, "Ошибка при выполнении запроса", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

