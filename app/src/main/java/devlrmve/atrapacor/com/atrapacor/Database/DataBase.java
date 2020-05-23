package devlrmve.atrapacor.com.atrapacor.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Marco on 13/12/2015.
 */
public class DataBase extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "DB_FINDCOLOR";

    //TABLE USERS
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " +
                    FeedReaderContract.FeedEntryUser.TABLE_NAME + " (" +
                    FeedReaderContract.FeedEntryUser.COLUMN_EMAIL_ENTRY_ID + " PRIMARY KEY," +
                    FeedReaderContract.FeedEntryUser.COLUMN_NAME + ", " +
                    FeedReaderContract.FeedEntryUser.COLUMN_URL_PHOTO + ", " +
                    FeedReaderContract.FeedEntryUser.COLUMN_PASS +
                    " )";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedReaderContract.FeedEntryUser.TABLE_NAME;

    //TABLE SCORES
    private static final String SQL_CREATE_ENTRIES_SCORE =
            "CREATE TABLE " +
                    FeedReaderContract.FeedEntryScore.TABLE_NAME + " (" +
                    FeedReaderContract.FeedEntryScore.COLUMN_ENTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                    FeedReaderContract.FeedEntryScore.COLUMN_DATE + " DATETIME ," +
                    FeedReaderContract.FeedEntryScore.COLUMN_SCORE + " INTEGER, " +
                    FeedReaderContract.FeedEntryScore.COLUMN_EMAIL_USER + ", " +
                    FeedReaderContract.FeedEntryScore.COLUMN_LEVEL + " INTEGER" +
                    " )";
    private static final String SQL_DELETE_ENTRIES_SCORE =
            "DROP TABLE IF EXISTS " + FeedReaderContract.FeedEntryScore.TABLE_NAME;

    //TABLE RECORD_NAME
    private static final String SQL_CREATE_ENTRIES_RECORD_NAME =
            "CREATE TABLE " +
                    FeedReaderContract.FeedEntryRecordName.TABLE_NAME + " (" +
                    FeedReaderContract.FeedEntryRecordName.COLUMN_ENTRY_ID + " INTEGER PRIMARY KEY," +
                    FeedReaderContract.FeedEntryRecordName.COLUMN_NAME + " ," +
                    FeedReaderContract.FeedEntryRecordName.COLUMN_DESCRIPTION +
                    " )";
    private static final String SQL_DELETE_ENTRIES_RECORD_NAME =
            "DROP TABLE IF EXISTS " + FeedReaderContract.FeedEntryRecordName.TABLE_NAME;

    //TABLE RECORDS
    private static final String SQL_CREATE_ENTRIES_RECORDS =
            "CREATE TABLE " +
                    FeedReaderContract.FeedEntryRecords.TABLE_NAME + " (" +
                    FeedReaderContract.FeedEntryRecords.COLUMN_EMAIL_USER_ENTRY_ID + " NOT NULL," +
                    FeedReaderContract.FeedEntryRecords.COLUMN_DATA + " DATETIME, " +
                    FeedReaderContract.FeedEntryRecords.COLUMN_RECORD_NAME +
                    " INT )";
    private static final String SQL_DELETE_ENTRIES_RECORDS =
            "DROP TABLE IF EXISTS " + FeedReaderContract.FeedEntryRecords.TABLE_NAME;

    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_ENTRIES_SCORE);
        db.execSQL(SQL_CREATE_ENTRIES_RECORD_NAME);
        db.execSQL(SQL_CREATE_ENTRIES_RECORDS);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(SQL_DELETE_ENTRIES_SCORE);
        db.execSQL(SQL_DELETE_ENTRIES_RECORD_NAME);
        db.execSQL(SQL_DELETE_ENTRIES_RECORDS);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}