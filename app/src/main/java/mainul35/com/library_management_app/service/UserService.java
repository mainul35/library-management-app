package mainul35.com.library_management_app.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import mainul35.com.library_management_app.database_util.DDL;
import mainul35.com.library_management_app.database_util.LibraryDatabaseHelper;
import mainul35.com.library_management_app.domain.Book;
import mainul35.com.library_management_app.domain.User;

public class UserService {
    private LibraryDatabaseHelper helper;
    private SQLiteDatabase db;

    public UserService(Context context) {
        helper = new LibraryDatabaseHelper(context);
    }

    public void open() {
        db = helper.getWritableDatabase();
    }

    public void close() {
        db.close();
    }

    public boolean create(User user) {
        this.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", user.getName());
        contentValues.put("username", user.getUsername());
        contentValues.put("password", user.getPassword());
        contentValues.put("userType", user.getUserType());
        Long status = db.insert(DDL.TBL_USER, null, contentValues);
        this.close();
        return status > 0 ? true : false;
    }

    public void update(User user) {
        this.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", user.getName());
//        contentValues.put("username", user.getUsername());
        contentValues.put("password", user.getPassword());
//        contentValues.put("userType", user.getUserType());
        db.update(DDL.TBL_USER, contentValues, "username="+ user.getUsername(), null);
        this.close();
    }

    public User findUserById(int id) {
        // get readable database as we are not inserting anything
        this.open();

        Cursor cursor = db.query("user",
                new String[]{"id", "name", "username", "password", "userType"},
                "id" + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        try {
            if (cursor != null) {
                cursor.moveToFirst();

                User user = new User(
                        cursor.getInt(cursor.getColumnIndex("id")),
                        cursor.getString(cursor.getColumnIndex("name")),
                        cursor.getString(cursor.getColumnIndex("username")),
                        cursor.getString(cursor.getColumnIndex("password")),
                        cursor.getInt(cursor.getColumnIndex("userType"))
                );

//         close the db connection
                cursor.close();
                this.close();
                return user;
            }else{
                this.close();
                return null;
            }

        }catch(CursorIndexOutOfBoundsException e){
            this.close();
            return null;
        }
    }

    public User findUserByUsername(String username) {
        // get readable database as we are not inserting anything
        this.open();

        Cursor cursor = db.query("user",
                new String[]{"id", "name", "username", "password", "userType"},
                "username" + "=?",
                new String[]{username}, null, null, null, null);

        try {
            if (cursor != null) {
                cursor.moveToFirst();

                User user = new User(
                        cursor.getInt(cursor.getColumnIndex("id")),
                        cursor.getString(cursor.getColumnIndex("name")),
                        cursor.getString(cursor.getColumnIndex("username")),
                        cursor.getString(cursor.getColumnIndex("password")),
                        cursor.getInt(cursor.getColumnIndex("userType"))
                );

//         close the db connection
                cursor.close();
                this.close();
                return user;
            }else{
                this.close();
                return null;
            }

        }catch(CursorIndexOutOfBoundsException e){
            this.close();
            return null;
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM user";

        this.open();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User(
                        cursor.getInt(cursor.getColumnIndex("id")),
                        cursor.getString(cursor.getColumnIndex("name")),
                        cursor.getString(cursor.getColumnIndex("username")),
                        cursor.getString(cursor.getColumnIndex("password")),
                        cursor.getInt(cursor.getColumnIndex("userType"))
                );


                users.add(user);
            } while (cursor.moveToNext());
        }

        // close db connection
        cursor.close();
        this.close();

        // return Books list
        return users;
    }

    public void delete(String username)
    {
        db.delete("user", "username='"+username+"'", null);
    }
}
