package com.example.booklibrary;

import static com.example.booklibrary.MainActivity.EXTRA_BOOK_ID;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class BookDetails extends AppCompatActivity {


    private ArrayList<Book> books = new ArrayList<>();
    private BookViewModel viewModel;
    private ImageView bookImage;
    private TextView bookName, bookAuthor, bookPages, bookDescription, bookYearOfPublication;
    private Button btnCurrentlyRead, btnAlreadyRead, btnAddToWishList, btnAddToFav, btnGetBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        initViews();

        viewModel = new ViewModelProvider(this).get(BookViewModel.class);
        Intent intent = getIntent();
        int bookId = intent.getIntExtra(EXTRA_BOOK_ID, -1);
        if(bookId == -1){
            finish();
            return;
        }

        viewModel.getBookById(bookId).observe(this, new Observer<Book>() {
            @Override
            public void onChanged(Book books) {
                if (books != null){
                    bookName.setText(books.getBookName());
                    bookAuthor.setText(books.getBookAuthor());
                    bookDescription.setText(books.getLongDescription());
                    bookPages.setText(String.valueOf(books.getBookPages()));
                    bookYearOfPublication.setText(String.valueOf(books.getYearOfPublication()));
                    Glide.with(BookDetails.this).asBitmap().load(books.getBookImage()).into(bookImage);
                }
            }
        });




    }




    private void initViews() {
        bookImage = findViewById(R.id.BookImage);

        bookName = findViewById(R.id.BookName);
        bookPages = findViewById(R.id.Pages);
        bookAuthor = findViewById(R.id.Author);
        bookYearOfPublication = findViewById(R.id.PublishedDate);
        bookDescription = findViewById(R.id.Description);

        btnAddToFav = findViewById(R.id.btnAddToFav);
        btnAddToWishList = findViewById(R.id.btnWishList);
        btnCurrentlyRead = findViewById(R.id.btnCurrentlyReading);
        btnAlreadyRead = findViewById(R.id.btnAlreadyRead);
        btnGetBook = findViewById(R.id.btnGetBook);
    }


}