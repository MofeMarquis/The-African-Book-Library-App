package com.example.booklibrary;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_BOOK_ID = "com.example.booklibrary_BOOK_ID";
    public static final String EXTRA_BOOK_NAME = "com.example.booklibrary_BOOK_NAME";
    public static final String EXTRA_BOOK_AUTHOR = "com.example.booklibrary_BOOK_AUTHOR";
    public static final String EXTRA_BOOK_PAGES = "com.example.booklibrary_BOOK_PAGES";
    public static final String EXTRA_BOOK_YEAR = "com.example.booklibrary_BOOK_YEAR";
    public static final String EXTRA_BOOK_IMAGE = "com.example.booklibrary_BOOK_IMAGE";
    public static final String EXTRA_BOOK_SHORT_DESCRIPTION = "com.example.booklibrary_BOOK_SHORT_DESCRIPTION";
    public static final String EXTRA_BOOK_LONG_DESCRIPTION = "com.example.booklibrary_EXTRA_BOOK_LONG_DESCRIPTION";

    private BookViewModel bookViewModel;
    private RecyclerView bookRecyclerView;
    private BookAdapter bookAdapters;
    //TODO: GET RECYCLERVIEW THROUGH ADAPTER

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bookViewModel = new ViewModelProvider(this).get(BookViewModel.class);

        bookRecyclerView = findViewById(R.id.bookRecyclerView);
        bookRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        bookAdapters = new BookAdapter();
        bookRecyclerView.setAdapter(bookAdapters);
        bookAdapters.setOnCardClickListener(new BookAdapter.onCardClickListener() {
            @Override
            public void onCardClick(Book book) {
                Intent intent = new Intent(MainActivity.this, BookDetails.class);
                intent.putExtra(MainActivity.EXTRA_BOOK_ID, book.getId());
                startActivity(intent);
            }
        });


        bookViewModel.getAllBooks().observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> books) {
                bookAdapters.submitList(books);
            }
        });


    }
}