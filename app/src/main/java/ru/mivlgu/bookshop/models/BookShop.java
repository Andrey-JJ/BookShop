package ru.mivlgu.bookshop.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class BookShop implements Serializable {

    private Long id;

    private String title;

    private AuthorShop author;

    private CategoryShop category;

    private PublisherShop publisher;

    private int publication_year;

    private int volume;

    private String image;

    private String description;

    private BigDecimal price;

    public BookShop(){}

    public BookShop(Long id, String title, AuthorShop author, CategoryShop category, PublisherShop publisher, int publication_year, int volume, String image, String description, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.publisher = publisher;
        this.publication_year = publication_year;
        this.volume = volume;
        this.image = image;
        this.description = description;
        this.price = price;
    }

    @Override
    public String toString() {
        return "BookShop{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", category=" + category +
                ", publisher=" + publisher +
                ", publication_year=" + publication_year +
                ", volume=" + volume +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AuthorShop getAuthor() {
        return author;
    }

    public void setAuthor(AuthorShop author) {
        this.author = author;
    }

    public CategoryShop getCategory() {
        return category;
    }

    public void setCategory(CategoryShop category) {
        this.category = category;
    }

    public PublisherShop getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherShop publisher) {
        this.publisher = publisher;
    }

    public int getPublication_year() {
        return publication_year;
    }

    public void setPublication_year(int publication_year) {
        this.publication_year = publication_year;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
