package ru.mivlgu.bookshop.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;
import ru.mivlgu.bookshop.models.BookingShop;

public interface BookingShopApi {
    @GET("bookings")
    Call<List<BookingShop>> getBookings();

    @POST("bookings/add")
    Call<Void> addBooking(@Body BookingShop bookingShop);

    @DELETE("bookings/delete/{id}")
    Call<Void> deleteBooking(@Path("id") Long id);
}
