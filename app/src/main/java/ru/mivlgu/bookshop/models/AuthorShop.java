package ru.mivlgu.bookshop.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class AuthorShop implements Parcelable {

    protected AuthorShop(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        lastname = in.readString();
        firstname = in.readString();
        midname = in.readString();
        books = in.createTypedArrayList(BookShop.CREATOR);
    }

    public static final Creator<AuthorShop> CREATOR = new Creator<AuthorShop>() {
        @Override
        public AuthorShop createFromParcel(Parcel in) {
            return new AuthorShop(in);
        }

        @Override
        public AuthorShop[] newArray(int size) {
            return new AuthorShop[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        dest.writeString(lastname);
        dest.writeString(firstname);
        dest.writeString(midname);
        dest.writeTypedList(books);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    private Long id;

    private String lastname;

    private String firstname;

    private String midname;

    private List<BookShop> books;

    public AuthorShop() {}

    public AuthorShop(Long id, String lastname, String firstname, String midname) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.midname = midname;
    }

    @Override
    public String toString() {
        return lastname+" "+firstname+" "+midname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMidname() {
        return midname;
    }

    public void setMidname(String midname) {
        this.midname = midname;
    }
}