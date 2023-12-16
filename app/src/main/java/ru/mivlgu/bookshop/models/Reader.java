package ru.mivlgu.bookshop.models;

public class Reader {

    private int id;

    private String lastname;

    private String firstname;

    private String midname;

    public Reader() {}

    public Reader(int id, String lastname, String firstname, String midname) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.midname = midname;
    }

    @Override
    public String toString() {
        return lastname +" "+firstname+" "+ midname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
