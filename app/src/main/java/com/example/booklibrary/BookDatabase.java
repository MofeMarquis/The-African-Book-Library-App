package com.example.booklibrary;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Book.class}, version = 1)
public abstract class BookDatabase extends RoomDatabase {

    private static BookDatabase bookInstance;
    public abstract BookDao bookDao();

    /*
    *
    * creating a new and single instance of the database class
    * if the new and oly instance is empty, create new one
    * and assign it to the room database builder
    * adding a fall back to destructive migration helps to create
    * new databases in case the new does not match the old one(avoid crash)
    * adding a callback to add initial data to database*/
    public static synchronized BookDatabase getInstance(Context context){
        if(bookInstance == null){
            bookInstance = Room.databaseBuilder(context.getApplicationContext(),
                            BookDatabase.class, "book_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();

        }
        return bookInstance;
    }


    private static final RoomDatabase.Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsync(bookInstance).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void>{
        private final BookDao bookDao;

        public PopulateDbAsync(BookDatabase bookDatabase) {
            this.bookDao = bookDatabase.bookDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            bookDao.insert(new Book(13.25, "A Grain of Wheat", "Ngũgĩ wa Thiong'o",
                    "book image", "A novel set in colonial Kenya on the eve of independence.",
                    "A novel set in colonial Kenya on the eve of independence."));

            bookDao.insert(new Book(12.50, "Americanah", "Chimamanda Ngozi Adichie",
                    "book image", " A novel about a Nigerian woman's experiences in the US.",
                    " Americanah is a novel that explores the experiences of a young Nigerian woman, " +
                            "Ifemelu, who emigrates to the United States and confronts issues of race, identity, and culture. " +
                            "As Ifemelu navigates life in America, she grapples with questions of belonging, assimilation, " +
                            "and the complexities of blackness in a country that still struggles with racism.\n" +
                            "\n" +
                            "Through Ifemelu's eyes, Adichie offers a searing critique of American society and culture, as well as a celebration of the resilience and vitality of African cultures. " +
                            "The novel is a poignant and powerful exploration of what it means to be African in the 21st century."));

            bookDao.insert(new Book(10.00, "Homegoing ", "Yaa Gyasi",
                    "book image", "A multigenerational saga tracing two sisters from Ghana to America.",
                    "Homegoing is a multigenerational saga that follows the descendants of two sisters from Ghana, one who marries a British slaver and the other who is sold into slavery. " +
                            "The novel spans several generations and continents, from the dungeons of the African coast to the cotton fields of Alabama, and explores the legacies of slavery and colonialism.\n" +
                            "\n" +
                            "Through vividly drawn characters and settings, Gyasi illuminates the ways in which slavery and racism have shaped the lives of Africans and African Americans. " +
                            "The novel is a powerful and poignant exploration of identity, family, and the enduring bonds of history."));

            bookDao.insert(new Book(13.11, "Nervous Condition", "Tsitsi Dangarembga",
                    "book image", "A coming-of-age novel set in Zimbabwe during the 1960s and 1970s.",
                    " Nervous Conditions is a coming-of-age novel set in Zimbabwe during the 1960s and 1970s that follows a young girl's struggle to pursue an education in a society that values boys over girls. " +
                            "Through the character of Tambu, Dangarembga explores the complexities of gender, class, and race in postcolonial Zimbabwe.\n" +
                            "\n" +
                            "The novel is a powerful critique of patriarchy and colonialism, as well as a celebration of the strength and resilience of African women. Nervous Conditions is a beautifully written and deeply moving" +
                            " novel that offers a powerful portrait of a society in transition."));

            bookDao.insert(new Book(15.20, "The Cairo Trilogy", "Naguib Mahfouz",
                    "book image", "A novel set in colonial Kenya on the eve of independence.",
                    "The Cairo Trilogy is a family saga set in Egypt in the early 20th century that follows the lives of three generations of a middle-class family. " +
                            "Through the characters of Ahmed, his wife Amina, and their children, Mahfouz portrays the social, political, and cultural changes that swept Egypt during this period.\n" +
                            "\n" +
                            "The novel is a powerful exploration of the complexities of family, love, and identity, as well as a poignant meditation on the forces that shape our lives."));

            bookDao.insert(new Book(11.50, "The Palm-Wine Drunkard", "Amos Tutuola",
                    "book image", " A novel about a man's journey through the spirit world.",
                    "The Palm-Wine Drinkard is a novel about a man's journey through the spirit world in search of his lost palm-wine tapster. " +
                            "Through vivid and surreal imagery, Tutuola creates a powerful and evocative portrait of African mythology and folklore.\n" +
                            "\n" +
                            "The novel is a celebration of the richness and diversity of African cultures, as well as a reminder of the enduring power of storytelling."));

            bookDao.insert(new Book(14.25, "We Need New Names", "NoViolet Bulawayo",
                    "book image", "A novel about a girl's journey from Zimbabwe to the US.",
                    "We Need New Names is a novel that follows the journey of Darling, a young girl who must navigate the complexities of life in Zimbabwe during " +
                            "the Mugabe regime, as well as her eventual emigration to the United States.\n" +
                            "\n" +
                            "Through Darling's story, Bulawayo offers a powerful and evocative portrait of the forces that shape our lives, as well as a poignant meditation on the " +
                            "nature of home and belonging."));

            bookDao.insert(new Book(13.25, "The Famished Road", "Ben Okri",
                    "book image", "A magical realist novel set in Nigeria.",
                    "The Famished Road is a magical realist novel that tells the story of Azaro, a spirit child who lives in a Nigerian slum. " +
                            "Through Azaro's eyes, Okri explores the complexities of Nigerian society, including poverty, corruption, and political violence.\n" +
                            "\n" +
                            "The novel is a powerful meditation on the nature of reality, as well as a celebration of the resilience and creativity of African cultures."));
            return null;
        }
    }


}
