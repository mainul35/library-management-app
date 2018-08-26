package mainul35.com.library_management_app.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import mainul35.com.library_management_app.database_util.DDL;
import mainul35.com.library_management_app.database_util.LibraryDatabaseHelper;
import mainul35.com.library_management_app.domain.User;

public class UserService {
    private LibraryDatabaseHelper helper;
    private SQLiteDatabase db;

    UserService(Context context) {
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
        contentValues.put("id", user.getId());
        contentValues.put("id", user.getId());
        contentValues.put("id", user.getId());
        contentValues.put("id", user.getId());
        contentValues.put("id", user.getId());
        Long status = db.insert(DDL.TBL_USER, null, contentValues);
        this.close();
        return status > 0 ? true : false;
    }
}
