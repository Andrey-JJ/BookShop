package ru.mivlgu.bookshop.ui.publisher;

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
import ru.mivlgu.bookshop.adapters.PublisherAdapter;
import ru.mivlgu.bookshop.api.PublisherShopApi;
import ru.mivlgu.bookshop.api.ServiceApi;
import ru.mivlgu.bookshop.databinding.FragmentPublisherBinding;
import ru.mivlgu.bookshop.models.*;
import ru.mivlgu.bookshop.models.view.PublisherViewModel;
import ru.mivlgu.bookshop.utils.BaseFragment;

public class PublisherFragment extends BaseFragment<FragmentPublisherBinding> {
    @Override
    protected Class<? extends ViewModel> getViewModelClass() { return PublisherViewModel.class; }

    @Override
    protected @NonNull ViewBinding createBinding(LayoutInflater inflater, ViewGroup container){
        return FragmentPublisherBinding.inflate(inflater, container, false);
    }

    @Override
    protected int getRecyclerViewId() { return R.id.rv_publisher; }

    @Override
    protected RecyclerView.Adapter createAdapter() { return new PublisherAdapter(); }

    @Override
    protected void fetchData(ViewModel viewModel) {
        PublisherShopApi publisherShopApi = ServiceApi.getPublishersApi();
        Call<List<PublisherShop>> call = publisherShopApi.getPublishers();

        call.enqueue(new Callback<List<PublisherShop>>() {
            @Override
            public void onResponse(Call<List<PublisherShop>> call, Response<List<PublisherShop>> response) {
                if (response.isSuccessful()) {
                    List<PublisherShop> publishers = response.body();
                    PublisherViewModel categoryViewModel = (PublisherViewModel) viewModel;
                    categoryViewModel.getText().observe(getViewLifecycleOwner(), data -> {
                        ((PublisherAdapter) getAdapter()).setData(publishers);
                    });
                } else {
                    System.out.println("НЕТ КОНТАКТА");
                }
            }

            @Override
            public void onFailure(Call<List<PublisherShop>> call, Throwable t) {
                try {
                    throw new Exception(t);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
