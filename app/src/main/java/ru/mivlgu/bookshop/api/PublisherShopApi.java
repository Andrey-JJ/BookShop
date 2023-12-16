package ru.mivlgu.bookshop.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import ru.mivlgu.bookshop.models.PublisherShop;

public interface PublisherShopApi {
    @GET("publishers")
    Call<List<PublisherShop>> getPublishers();
}
