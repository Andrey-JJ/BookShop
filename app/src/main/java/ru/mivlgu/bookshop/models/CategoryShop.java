package ru.mivlgu.bookshop.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class CategoryShop implements Parcelable {

    protected CategoryShop(Parcel in) {
        name = in.readString();
    }

    public static final Creator<CategoryShop> CREATOR = new Creator<CategoryShop>() {
        @Override
        public CategoryShop createFromParcel(Parcel in) {
            return new CategoryShop(in);
        }

        @Override
        public CategoryShop[] newArray(int size) {
            return new CategoryShop[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }

    private int id;

    private String name;

    //private List<BookShop> books;

    public CategoryShop() {}

    public CategoryShop(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
