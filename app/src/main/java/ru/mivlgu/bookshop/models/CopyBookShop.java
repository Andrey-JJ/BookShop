package ru.mivlgu.bookshop.models;


public class CopyBookShop {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BookShop getBookshop() {
        return bookshop;
    }

    public void setBookshop(BookShop bookshop) {
        this.bookshop = bookshop;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public CopyBookShop() {
    }

    public CopyBookShop(int id, BookShop bookshop, boolean available) {
        this.id = id;
        this.bookshop = bookshop;
        this.available = available;
    }

    private int id;

    private BookShop bookshop;

    private boolean available;
}
