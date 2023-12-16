package ru.mivlgu.bookshop.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import ru.mivlgu.bookshop.models.BookingShop;

public interface BookingShopApi {
    @GET("bookings")
    Call<List<BookingShop>> getBookings();
}
