package com.example.booklibrary;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class BookViewModel extends AndroidViewModel {

    private final LiveData<List<Book>> allBooks;

    private BookRepository bookRepository;

    public BookViewModel(@NonNull Application application) {
        super(application);
        bookRepository = new BookRepository(application);
        allBooks = bookRepository.getAllBooks();
    }

    public LiveData<List<Book>> getAllBooks() {
        return allBooks;
    }
    public LiveData<Book> getBookById(int bookId){
        return bookRepository.getBookById(bookId);
    }
}
