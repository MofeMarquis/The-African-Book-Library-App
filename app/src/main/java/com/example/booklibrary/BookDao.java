package com.example.booklibrary;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BookDao {

    @Insert
    void insert(Book book);

    @Query("SELECT * FROM bookTable")
    LiveData<List<Book>> getAllBooks();

    @Query("DELETE FROM bookTable")
    void deleteAllBooks();

    @Query("SELECT * FROM bookTable WHERE id=:bookId")
    LiveData<Book>getBookById(int bookId);
}
