package ru.mivlgu.bookshop.ui.book;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.mivlgu.bookshop.R;
import ru.mivlgu.bookshop.adapters.BookAdapter;
import ru.mivlgu.bookshop.api.BookShopApi;
import ru.mivlgu.bookshop.api.ServiceApi;
import ru.mivlgu.bookshop.databinding.FragmentBooksBinding;
import ru.mivlgu.bookshop.models.BookShop;
import ru.mivlgu.bookshop.models.view.BookViewModel;
import ru.mivlgu.bookshop.utils.BaseFragment;

public class BooksFragment extends BaseFragment<FragmentBooksBinding> {
    private Spinner spinnerPublisher, spinnerAuthor, spinnerCategory;
    @Override
    protected Class<? extends ViewModel> getViewModelClass() {
        return BookViewModel.class;
    }

    @Override
    protected @NonNull ViewBinding createBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentBooksBinding.inflate(inflater, container, false);
    }

    @Override
    protected int getRecyclerViewId() {
        return R.id.rv_book;
    }

    @Override
    protected RecyclerView.Adapter createAdapter() {
        BookAdapter bookAdapter = new BookAdapter();
        bookAdapter.setOnItemClickListener(book -> {
            Intent intent = new Intent(requireContext(), BookDetailActivity.class);
            intent.putExtra("book", book);
            startActivity(intent);
        });
        return bookAdapter;
    }

    @Override
    protected void fetchData(ViewModel viewModel) {
        BookShopApi bookShopApi = ServiceApi.getBooksApi();
        Call<List<BookShop>> call = bookShopApi.getBooks();

        call.enqueue(new Callback<List<BookShop>>() {
            @Override
            public void onResponse(Call<List<BookShop>> call, Response<List<BookShop>> response) {
                if (response.isSuccessful()) {
                    List<BookShop> books = response.body();
                    BookViewModel bookViewModel = (BookViewModel) viewModel;
                    bookViewModel.getText().observe(getViewLifecycleOwner(), data -> {
                        ((BookAdapter) getAdapter()).setData(books);
                    });
                    setupSpinners(books);
                } else {
                    System.out.println("НЕТ КОНТАКТА");
                }
            }

            @Override
            public void onFailure(Call<List<BookShop>> call, Throwable t) {
                try {
                    throw new Exception(t);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    //Фильтрация данных
    //
    private void setupSpinners(List<BookShop> books){
        spinnerPublisher = getView().findViewById(R.id.spinnerPublisher);
        spinnerAuthor = getView().findViewById(R.id.spinnerAuthor);
        spinnerCategory = getView().findViewById(R.id.spinnerCategory);

        // Получение уникальных издателей, авторов и категорий из списка книг
        List<String> uniquePublishers = getUniquePublishers(books);
        List<String> uniqueAuthors = getUniqueAuthors(books);
        List<String> uniqueCategories = getUniqueCategories(books);

        // Настройка адаптеров и присвоение их Spinner'ам
        ArrayAdapter<String> publisherAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_dropdown_item, uniquePublishers);
        ArrayAdapter<String> authorAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_dropdown_item, uniqueAuthors);
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_dropdown_item, uniqueCategories);

        spinnerPublisher.setAdapter(publisherAdapter);
        spinnerAuthor.setAdapter(authorAdapter);
        spinnerCategory.setAdapter(categoryAdapter);

        // Добавление слушателей для фильтрации данных при выборе в Spinner'ах
        spinnerPublisher.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedPublisher = (String) spinnerPublisher.getSelectedItem();
                ((BookAdapter) getAdapter()).filterByPublisher(selectedPublisher);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Ничего не делаем, если ничего не выбрано
            }
        });

        spinnerAuthor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedAuthor = (String) spinnerAuthor.getSelectedItem();
                if (selectedAuthor.equals("Выберите автора")){
                    ((BookAdapter) getAdapter()).filterByAuthor(null);
                }
                else {
                    ((BookAdapter) getAdapter()).filterByAuthor(selectedAuthor);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Ничего не делаем, если ничего не выбрано
            }
        });

        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedCategory = (String) spinnerCategory.getSelectedItem();
                if(selectedCategory.equals("Выберите категорию")){
                    ((BookAdapter) getAdapter()).filterByCategory(null);
                }
                else {
                    ((BookAdapter) getAdapter()).filterByCategory(selectedCategory);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Ничего не делаем, если ничего не выбрано
            }
        });
    }

    private List<String> getUniqueAuthors(List<BookShop> books) {
        List<String> uniqueAuthors = new ArrayList<>();
        Set<String> seenAuthors = new HashSet<>();

        for (BookShop book : books) {
            String author = book.getAuthor().toString();
            if (!seenAuthors.contains(author)) {
                seenAuthors.add(author);
                uniqueAuthors.add(author);
            }
        }

        // Добавление текста "Выберите автора", если книги принадлежат разным авторам
        if (uniqueAuthors.size() > 1) {
            uniqueAuthors.add(0, "Выберите автора");
        }

        return uniqueAuthors;
    }

    private List<String> getUniquePublishers(List<BookShop> books) {
        List<String> uniquePublishers = new ArrayList<>();
        Set<String> seenPublishers = new HashSet<>();

        for (BookShop book : books) {
            String publisher = book.getPublisher().getName();
            if (!seenPublishers.contains(publisher)) {
                seenPublishers.add(publisher);
                uniquePublishers.add(publisher);
            }
        }

        // Добавление текста "Выберите издателя", если книги принадлежат разным издателям
        if (uniquePublishers.size() > 1) {
            uniquePublishers.add(0, "Выберите издателя");
        }

        return uniquePublishers;
    }

    private List<String> getUniqueCategories(List<BookShop> books) {
        List<String> uniqueCategories = new ArrayList<>();
        Set<String> seenCategories = new HashSet<>();

        for (BookShop book : books) {
            String category = book.getCategory().getName();
            if (!seenCategories.contains(category)) {
                seenCategories.add(category);
                uniqueCategories.add(category);
            }
        }

        // Добавление текста "Выберите категорию", если книги принадлежат разным категориям
        if (uniqueCategories.size() > 1) {
            uniqueCategories.add(0, "Выберите категорию");
        }

        return uniqueCategories;
    }
}
