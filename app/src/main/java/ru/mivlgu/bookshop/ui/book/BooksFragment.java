package ru.mivlgu.bookshop.ui.book;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import java.util.List;

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
        return new BookAdapter(getFragmentManager());
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
                    BookViewModel computerViewModel = (BookViewModel) viewModel;
                    computerViewModel.getText().observe(getViewLifecycleOwner(), data -> {
                        ((BookAdapter) getAdapter()).setData(books);
                    });
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
}
