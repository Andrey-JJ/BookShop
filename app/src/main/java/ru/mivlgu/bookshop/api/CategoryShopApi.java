package ru.mivlgu.bookshop.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import ru.mivlgu.bookshop.models.CategoryShop;

public interface CategoryShopApi {
    @GET("categories")
    Call<List<CategoryShop>> getCategories();
}
