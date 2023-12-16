package ru.mivlgu.bookshop.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceApi {
    private static final String BASE_URL = "http://192.168.56.1:8080/api/shop/";

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static BookShopApi getBooksApi() { return retrofit.create(BookShopApi.class); }

    public static AuthorShopApi getAuthorsApi() { return retrofit.create(AuthorShopApi.class); }

    public static CategoryShopApi getCategoriesApi() { return retrofit.create(CategoryShopApi.class); }

    public static PublisherShopApi getPublishersApi() { return retrofit.create(PublisherShopApi.class); }
}
