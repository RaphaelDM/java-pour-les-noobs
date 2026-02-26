package org.example.domain;

import java.util.Objects;

public class Book {

    private final int ibsn;
    private String title;
    private String author;
    private int year;

    public Book(int ibsn, String title, String author, int year) {
        this.ibsn = ibsn;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public int getIbsn() {
        return ibsn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return ibsn == book.ibsn;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ibsn);
    }

    @Override
    public String toString() {
        return "Book{" + "ibsn=" + ibsn + ", title='" + title + '\'' + ", author='" + author + '\'' + ", year=" + year + '}';
    }
}
