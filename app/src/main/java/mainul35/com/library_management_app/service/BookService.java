package mainul35.com.library_management_app.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import mainul35.com.library_management_app.database_util.DDL;
import mainul35.com.library_management_app.database_util.LibraryDatabaseHelper;
import mainul35.com.library_management_app.domain.Book;
import mainul35.com.library_management_app.domain.User;

public class BookService {

    private LibraryDatabaseHelper helper;
    private SQLiteDatabase db;

    public BookService(Context context) {
        helper = new LibraryDatabaseHelper(context);
    }

    public void open() {
        db = helper.getWritableDatabase();
    }

    public void close() {
        db.close();
    }

    public boolean create(Book book) {
        this.open();

//        "id INTEGER PRIMARY KEY AUTOINCREMENT,"
//                + "name String,"
//                + "author_name String,"
//                + "isbn String,"
//                + "publication String,"
//                + "publishing_date String,"
//                + "edition String"
//
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", book.getName());
        contentValues.put("isbn", book.getIsbn());
        contentValues.put("author_name", book.getAuthorName());
        contentValues.put("publication", book.getPublication());
        contentValues.put("publishing_date",book.getPublishingDate());
        contentValues.put("edition", book.getEdition());
        Long status = db.insert("book", null, contentValues);
        this.close();
        return status > 0 ? true : false;
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM book";

        this.open();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Book Book = new Book();
                Book.setId(cursor.getInt(cursor.getColumnIndex("id")));
                Book.setName(cursor.getString(cursor.getColumnIndex("name")));
                Book.setIsbn(cursor.getString(cursor.getColumnIndex("isbn")));
                Book.setAuthorName(cursor.getString(cursor.getColumnIndex("author_name")));
                Book.setPublication(cursor.getString(cursor.getColumnIndex("publication")));
                Book.setPublishingDate(cursor.getString(cursor.getColumnIndex("publishing_date")));
                Book.setEdition(cursor.getString(cursor.getColumnIndex("edition")));

                books.add(Book);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return Books list
        return books;
    }
}
