package ru.mivlgu.bookshop.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class PublisherShop implements Parcelable {

    protected PublisherShop(Parcel in) {
        name = in.readString();
    }

    public static final Creator<PublisherShop> CREATOR = new Creator<PublisherShop>() {
        @Override
        public PublisherShop createFromParcel(Parcel in) {
            return new PublisherShop(in);
        }

        @Override
        public PublisherShop[] newArray(int size) {
            return new PublisherShop[size];
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

    private List<BookShop> books;

    public PublisherShop() {}

    public PublisherShop(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Publisher{" +
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
