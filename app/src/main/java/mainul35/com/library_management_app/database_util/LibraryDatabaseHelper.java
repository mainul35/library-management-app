package mainul35.com.library_management_app.database_util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LibraryDatabaseHelper extends SQLiteOpenHelper{
    public LibraryDatabaseHelper(Context context) {
        super(context, "Library_DB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DDL.CREATE_TABLE_USER);
        sqLiteDatabase.execSQL(DDL.CREATE_TABLE_BOOK);
        sqLiteDatabase.execSQL(DDL.CREATE_TABLE_COMMENTS);
        sqLiteDatabase.execSQL(DDL.CREATE_TABLE_BOOK_REACTIONS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
