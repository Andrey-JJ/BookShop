package ru.mivlgu.bookshop.ui.book;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.io.Serializable;

import ru.mivlgu.bookshop.R;
import ru.mivlgu.bookshop.models.BookShop;

public class BookDetailFragment extends Fragment {

    public static final String ARG_BOOK = "book";

    private BookShop book;

    public BookDetailFragment() {}

    public static BookDetailFragment newInstance(BookShop book) {
        BookDetailFragment fragment = new BookDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_BOOK, (Serializable) book);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            book = (BookShop) getArguments().getSerializable(ARG_BOOK);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Здесь создайте макет для отображения подробной информации о книге
        View view = inflater.inflate(R.layout.fragment_book_detail, container, false);

        // Заполните макет данными о книге
        ImageView bookImage = view.findViewById(R.id.book_image);
        TextView bookTitle = view.findViewById(R.id.book_title);
        TextView bookAuthor = view.findViewById(R.id.book_author);
        TextView bookCategory = view.findViewById(R.id.book_category);
        TextView bookPublisher = view.findViewById(R.id.book_publisher);
        TextView bookVolume = view.findViewById(R.id.book_volume);
        TextView bookDescriprion = view.findViewById(R.id.book_description);

        bookTitle.setText(String.format("Название: %s", book.getTitle()));
        bookAuthor.setText(String.format("Автор: %s", book.getAuthor().toString()));
        bookCategory.setText(String.format("Категория: %s", book.getCategory().toString()));
        bookPublisher.setText(String.format("Издатель: %s | %s",book.getPublisher().toString(), book.getPublication_year()));
        bookVolume.setText(String.format("Объем книги: %s", book.getVolume()));
        bookDescriprion.setText(String.format("Описание книги \n%s", book.getDescription()));

        return view;
    }
}

