package ru.mivlgu.bookshop.ui.booking;

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
import ru.mivlgu.bookshop.adapters.BookingAdapter;
import ru.mivlgu.bookshop.api.BookingShopApi;
import ru.mivlgu.bookshop.api.ServiceApi;
import ru.mivlgu.bookshop.databinding.FragmentBookingBinding;
import ru.mivlgu.bookshop.models.BookingShop;
import ru.mivlgu.bookshop.models.view.BookingViewModel;
import ru.mivlgu.bookshop.utils.BaseFragment;

public class BookingFragment extends BaseFragment<FragmentBookingBinding> {
    @Override
    protected Class<? extends ViewModel> getViewModelClass() {
        return BookingViewModel.class;
    }

    @Override
    protected @NonNull ViewBinding createBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentBookingBinding.inflate(inflater, container, false);
    }

    @Override
    protected int getRecyclerViewId() {
        return R.id.rv_booking;
    }

    @Override
    protected RecyclerView.Adapter createAdapter() {
        return new BookingAdapter();
    }

    @Override
    protected void fetchData(ViewModel viewModel) {
        BookingShopApi bookingShopApi = ServiceApi.getBookingsApi();
        Call<List<BookingShop>> call = bookingShopApi.getBookings();

        call.enqueue(new Callback<List<BookingShop>>() {
            @Override
            public void onResponse(Call<List<BookingShop>> call, Response<List<BookingShop>> response) {
                if (response.isSuccessful()) {
                    List<BookingShop> bookings = response.body();
                    BookingViewModel bookingViewModel = (BookingViewModel) viewModel;
                    bookingViewModel.getText().observe(getViewLifecycleOwner(), data -> {
                        ((BookingAdapter) getAdapter()).setData(bookings);
                    });
                } else {
                    System.out.println("НЕТ КОНТАКТА");
                }
            }

            @Override
            public void onFailure(Call<List<BookingShop>> call, Throwable t) {
                try {
                    throw new Exception(t);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
