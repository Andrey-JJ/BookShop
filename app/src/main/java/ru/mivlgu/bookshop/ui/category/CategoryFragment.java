package ru.mivlgu.bookshop.ui.category;

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
import ru.mivlgu.bookshop.adapters.CategoryAdapter;
import ru.mivlgu.bookshop.api.CategoryShopApi;
import ru.mivlgu.bookshop.api.ServiceApi;
import ru.mivlgu.bookshop.databinding.FragmentCategoryBinding;
import ru.mivlgu.bookshop.models.CategoryShop;
import ru.mivlgu.bookshop.models.view.CategoryViewModel;
import ru.mivlgu.bookshop.utils.BaseFragment;

public class CategoryFragment extends BaseFragment<FragmentCategoryBinding> {
    @Override
    protected Class<? extends ViewModel> getViewModelClass() {
        return CategoryViewModel.class;
    }

    @Override
    protected @NonNull ViewBinding createBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentCategoryBinding.inflate(inflater, container, false);
    }

    @Override
    protected int getRecyclerViewId() {
        return R.id.rv_category;
    }

    @Override
    protected RecyclerView.Adapter createAdapter() {
        return new CategoryAdapter();
    }

    @Override
    protected void fetchData(ViewModel viewModel) {
        CategoryShopApi categoryShopApi = ServiceApi.getCategoriesApi();
        Call<List<CategoryShop>> call = categoryShopApi.getCategories();

        call.enqueue(new Callback<List<CategoryShop>>() {
            @Override
            public void onResponse(Call<List<CategoryShop>> call, Response<List<CategoryShop>> response) {
                if (response.isSuccessful()) {
                    List<CategoryShop> categories = response.body();
                    CategoryViewModel categoryViewModel = (CategoryViewModel) viewModel;
                    categoryViewModel.getText().observe(getViewLifecycleOwner(), data -> {
                        ((CategoryAdapter) getAdapter()).setData(categories);
                    });
                } else {
                    System.out.println("НЕТ КОНТАКТА");
                }
            }

            @Override
            public void onFailure(Call<List<CategoryShop>> call, Throwable t) {
                try {
                    throw new Exception(t);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
