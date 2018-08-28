package mainul35.com.library_management_app.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
        contentValues.put("name", user.getName());
        contentValues.put("username", user.getUsername());
        contentValues.put("password", user.getPassword());
        contentValues.put("userType", user.getUserType());
        Long status = db.insert(DDL.TBL_USER, null, contentValues);
        this.close();
        return status > 0 ? true : false;
    }

    public User findUserById(int id) {
        // get readable database as we are not inserting anything
        this.open();

//        Cursor cursor = db.query(Note.TABLE_NAME,
//                new String[]{Note.COLUMN_ID, Note.COLUMN_NOTE, Note.COLUMN_TIMESTAMP},
//                Note.COLUMN_ID + "=?",
//                new String[]{String.valueOf(id)}, null, null, null, null);
//
//        if (cursor != null)
//            cursor.moveToFirst();
//
//        // prepare note object
//        Note note = new Note(
//                cursor.getInt(cursor.getColumnIndex(Note.COLUMN_ID)),
//                cursor.getString(cursor.getColumnIndex(Note.COLUMN_NOTE)),
//                cursor.getString(cursor.getColumnIndex(Note.COLUMN_TIMESTAMP)));

        // close the db connection
//        cursor.close();
        this.close();
        return null;
    }
}
