package ru.mivlgu.bookshop.api;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.mivlgu.bookshop.models.BookingShop;

public class ServiceApi {
    //Строка для обращения к api
    private static final String BASE_URL = "http://192.168.56.1:8080/api/shop/";

    //Создание retrofit для получения запросов
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    //Получение всех Книг
    public static BookShopApi getBooksApi() { return retrofit.create(BookShopApi.class); }

    //Получение всех Авторов
    public static AuthorShopApi getAuthorsApi() { return retrofit.create(AuthorShopApi.class); }

    //Получение всех Категорий
    public static CategoryShopApi getCategoriesApi() { return retrofit.create(CategoryShopApi.class); }

    //Получение всех Издателей
    public static PublisherShopApi getPublishersApi() { return retrofit.create(PublisherShopApi.class); }

    //Получение всех Броней
    public static BookingShopApi getBookingsApi() { return retrofit.create(BookingShopApi.class); }

    //Добавление Брони на экземпляр книги
    public static void addBooking(BookingShop bookingShop, Callback<Void> callback) {
        getBookingsApi().addBooking(bookingShop).enqueue(callback);
    }

    //Удаление Брони на экземпляр книги
    public static void deleteBooking(Long id, Callback<Void> callback) {
        getBookingsApi().deleteBooking(id).enqueue(callback);
    }
}
