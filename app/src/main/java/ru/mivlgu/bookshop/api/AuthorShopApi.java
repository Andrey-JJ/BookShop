package ru.mivlgu.bookshop.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import ru.mivlgu.bookshop.models.AuthorShop;

public interface AuthorShopApi {
    @GET("authors")
    Call<List<AuthorShop>> getAuthors();
}
