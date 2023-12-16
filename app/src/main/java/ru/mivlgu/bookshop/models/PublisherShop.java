package ru.mivlgu.bookshop.models;

import java.util.List;

public class PublisherShop {
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
