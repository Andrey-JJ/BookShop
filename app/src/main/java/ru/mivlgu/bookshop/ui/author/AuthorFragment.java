package ru.mivlgu.bookshop.ui.author;

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
import ru.mivlgu.bookshop.adapters.AuthorAdapter;
import ru.mivlgu.bookshop.api.AuthorShopApi;
import ru.mivlgu.bookshop.api.ServiceApi;
import ru.mivlgu.bookshop.databinding.FragmentAuthorBinding;
import ru.mivlgu.bookshop.models.AuthorShop;
import ru.mivlgu.bookshop.models.view.AuthorViewModel;
import ru.mivlgu.bookshop.utils.BaseFragment;

public class AuthorFragment extends BaseFragment<FragmentAuthorBinding> {
    @Override
    protected Class<? extends ViewModel> getViewModelClass() {
        return AuthorViewModel.class;
    }

    @Override
    protected @NonNull ViewBinding createBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentAuthorBinding.inflate(inflater, container, false);
    }

    @Override
    protected int getRecyclerViewId() {
        return R.id.rv_author;
    }

    @Override
    protected RecyclerView.Adapter createAdapter() {
        return new AuthorAdapter();
    }

    @Override
    protected void fetchData(ViewModel viewModel) {
        AuthorShopApi authorShopApi = ServiceApi.getAuthorsApi();
        Call<List<AuthorShop>> call = authorShopApi.getAuthors();

        call.enqueue(new Callback<List<AuthorShop>>() {
            @Override
            public void onResponse(Call<List<AuthorShop>> call, Response<List<AuthorShop>> response) {
                if (response.isSuccessful()) {
                    List<AuthorShop> authors = response.body();
                    AuthorViewModel authorViewModel = (AuthorViewModel) viewModel;
                    authorViewModel.getText().observe(getViewLifecycleOwner(), data -> {
                        ((AuthorAdapter) getAdapter()).setData(authors);
                    });
                } else {
                    System.out.println("НЕТ КОНТАКТА");
                }
            }

            @Override
            public void onFailure(Call<List<AuthorShop>> call, Throwable t) {
                try {
                    throw new Exception(t);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
