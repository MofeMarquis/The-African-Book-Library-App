package com.example.booklibrary;

import static com.example.booklibrary.MainActivity.EXTRA_BOOK_AUTHOR;
import static com.example.booklibrary.MainActivity.EXTRA_BOOK_ID;
import static com.example.booklibrary.MainActivity.EXTRA_BOOK_IMAGE;
import static com.example.booklibrary.MainActivity.EXTRA_BOOK_LONG_DESCRIPTION;
import static com.example.booklibrary.MainActivity.EXTRA_BOOK_NAME;
import static com.example.booklibrary.MainActivity.EXTRA_BOOK_PAGES;
import static com.example.booklibrary.MainActivity.EXTRA_BOOK_SHORT_DESCRIPTION;
import static com.example.booklibrary.MainActivity.EXTRA_BOOK_YEAR;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends ListAdapter<Book, BookAdapter.BookHolder> {


    public static onCardClickListener listener;
    private LiveData<List<Book>> allBooks;
    private Context myContext;

    protected BookAdapter() {
        super(DIFF_CALLBACK);
    }

    public static final DiffUtil.ItemCallback<Book> DIFF_CALLBACK = new DiffUtil.ItemCallback<Book>() {
        @Override
        public boolean areItemsTheSame(@NonNull Book oldItem, @NonNull Book newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Book oldItem, @NonNull Book newItem) {
            return oldItem.getBookName().equals(newItem.getBookName()) &&
                    oldItem.getBookAuthor().equals(newItem.getBookAuthor()) &&
                    oldItem.getBookImage().equals(newItem.getBookImage()) &&
                    oldItem.getBookPrice() == newItem.getBookPrice() &&
                    oldItem.getShortDescription().equals(newItem.getShortDescription()) &&
                    oldItem.getLongDescription().equals(newItem.getLongDescription());
        }
    };

    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View holder = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_item, parent, false);
        myContext = parent.getContext();
        return new BookHolder(holder);
    }

    @Override
    public void onBindViewHolder(@NonNull BookHolder holder, int position) {
        /*
        * set a current book to the position of the current item*/

        Book currentBook = getItem(position);
        holder.bookName.setText(currentBook.getBookName());
        holder.authorName.setText(currentBook.getBookAuthor());
        holder.shortDescription.setText(currentBook.getShortDescription());


        //load book image
        Glide.with(myContext)
                .asBitmap()
                .load(currentBook.getBookImage())
                .into(holder.bookPicture);

        holder.downArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransitionManager.beginDelayedTransition(holder.cardView);
                currentBook.setExpanded(true);
                holder.downArrow.setVisibility(View.GONE);
                holder.upArrow.setVisibility(View.VISIBLE);
                holder.expandedRelativeView.setVisibility(View.VISIBLE);
            }
        });

        holder.upArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentBook.setExpanded(false);
                holder.downArrow.setVisibility(View.VISIBLE);
                holder.upArrow.setVisibility(View.GONE);
                holder.expandedRelativeView.setVisibility(View.GONE);
            }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null && holder.getAdapterPosition() != RecyclerView.NO_POSITION){
                    listener.onCardClick(currentBook);
                }
            }
        });


    }

    class BookHolder extends RecyclerView.ViewHolder {

        private final TextView authorName, bookName, shortDescription;
        private ImageView bookPicture, upArrow, downArrow;
        private final RelativeLayout collapsedRelativeView, parentRelativeView, expandedRelativeView;
        private CardView cardView;


        public BookHolder(@NonNull View itemView) {
            super(itemView);

            authorName = itemView.findViewById(R.id.authorName);
            bookName = itemView.findViewById(R.id.bookName);
            bookPicture = itemView.findViewById(R.id.bookImage);
            shortDescription = itemView.findViewById(R.id.shortDescriptionText);
            upArrow = itemView.findViewById(R.id.upArrow);
            downArrow = itemView.findViewById(R.id.downArrow);
            collapsedRelativeView = itemView.findViewById(R.id.collapsedRelativeLayout);
            expandedRelativeView = itemView.findViewById(R.id.expandedRelativeLayout);
            parentRelativeView = itemView.findViewById(R.id.parentRelativeLayout);
            cardView = itemView.findViewById(R.id.parentCardView);




        }

    }



    public interface onCardClickListener {
        void onCardClick(Book book);
    }
    public void setOnCardClickListener(onCardClickListener listeners) {
        listener = listeners;
    }

}
