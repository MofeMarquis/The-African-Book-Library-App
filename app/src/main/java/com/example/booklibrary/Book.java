package com.example.booklibrary;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "bookTable")
public class Book {

    public Book(double bookPrice, String bookName, String bookAuthor, String bookImage, String shortDescription, String longDescription) {
        this.bookPrice = bookPrice;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookImage = bookImage;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.isExpanded = false;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "book_price")
    private double bookPrice;

    @ColumnInfo(name = "book_name")
    private String bookName;

    @ColumnInfo(name = "book_Author")
    private String bookAuthor;

    @ColumnInfo(name = "book_image")
    private String bookImage;

    @ColumnInfo(name = "book pages")
    private int bookPages;

    @ColumnInfo(name = "year_of_publication")
    private int yearOfPublication;

    @ColumnInfo(name = "short_description")
    private String shortDescription;

    @ColumnInfo(name = "long_description")
    private String longDescription;

    @Ignore
    private boolean isExpanded;


    public void setId(int id) {
        this.id = id;
    }

    public void setBookPrice(double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setBookImage(String bookImage) {
        this.bookImage = bookImage;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public int getId() {
        return id;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public double getBookPrice() {
        return bookPrice;
    }

    public String getBookName() {
        return bookName;
    }

    public String getBookImage() {
        return bookImage;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public int get(int adapterPosition) {
        return adapterPosition;
    }

    public int getBookPages() {
        return bookPages;
    }

    public void setBookPages(int bookPages) {
        this.bookPages = bookPages;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }
}
