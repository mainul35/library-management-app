package mainul35.com.library_management_app.database_util;

public class DDL {
    public static final String TBL_USER = "User";
    public static final String CREATE_TABLE_USER = "CREATE TABLE " + TBL_USER + "("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "name String,"
            + "username String,"
            + "password String,"
            + "userType integer"
            + ")";

    public static final String CREATE_TABLE_BOOK = "CREATE TABLE book("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "name String,"
            + "author_name String,"
            + "isbn String,"
            + "publication String,"
            + "publishing_date` DATETIME,"
            + "edition String,"
            + "image blob"
            + ")";

    public static final String CREATE_TABLE_COMMENTS = "CREATE TABLE comments("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "book_id integer,"
            + "commenter_id integer,"
            + "comment_description String"
            + ")";

    public static final String CREATE_TABLE_BOOK_REACTIONS = "CREATE TABLE book_reactions("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "book_id integer,"
            + "reactor_id integer,"
            + "reaction_type integer"
            + ")";
}
