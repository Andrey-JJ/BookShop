package ru.mivlgu.bookshop.models;

import java.util.List;

public class AuthorShop {
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