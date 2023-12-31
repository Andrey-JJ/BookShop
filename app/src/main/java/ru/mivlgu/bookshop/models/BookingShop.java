package ru.mivlgu.bookshop.models;

public class BookingShop {
    private Long id;

    private BookShop book;

    private Reader reader;

    public BookingShop() {}

    public BookingShop(Long id, BookShop book, Reader reader) {
        this.id = id;
        this.book = book;
        this.reader = reader;
    }

    public BookingShop(BookShop book, Reader reader) {
        this.book = book;
        this.reader = reader;
    }

    @Override
    public String toString() {
        return "BookingShop{" +
                "id=" + id +
                ", book=" + book +
                ", reader=" + reader +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BookShop getBook() {
        return book;
    }

    public void setBook(BookShop book) {
        this.book = book;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }
}
