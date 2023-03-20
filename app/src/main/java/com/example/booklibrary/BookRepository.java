package com.example.booklibrary;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class BookRepository {

    /*
    *
    * variables to access a list of livedata books
    * and a bookDao object to access the database methods*/
    private LiveData<List<Book>> allBooks;
    private BookDao bookDao;

    public BookRepository(Application application) {
        /*
        * create an instance of the database here
        * to access the database values
        *
        * use the bookDao object to access the bookDao*/
        BookDatabase database = BookDatabase.getInstance(application);
        bookDao = database.bookDao();
        allBooks = bookDao.getAllBooks();
    }


    public void insert(Book book){
        new InsertBookAsync(bookDao).execute(book);
    }

    LiveData<List<Book>>getAllBooks(){
        return allBooks;
    }

    LiveData<Book> getBookById(int bookId){
        return bookDao.getBookById(bookId);
    }



    public static class InsertBookAsync extends AsyncTask<Book, Void, Void>{
        private final BookDao bookDao;

        public InsertBookAsync(BookDao bookDao) {
            this.bookDao = bookDao;
        }

        @Override
        protected Void doInBackground(Book... books) {
            bookDao.insert(books[0]);
            return null;
        }
    }
}
