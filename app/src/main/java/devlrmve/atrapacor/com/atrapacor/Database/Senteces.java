package devlrmve.atrapacor.com.atrapacor.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;

import devlrmve.atrapacor.com.atrapacor.Utils.CurrentDate;
import devlrmve.atrapacor.com.atrapacor.Utils.Records;
import devlrmve.atrapacor.com.atrapacor.Utils.RecordsName;
import devlrmve.atrapacor.com.atrapacor.Utils.Scores;

/**
 * Created by Marco on 16/12/2015.
 */
public class Senteces {

    //INSERT
    //Insert users
    public static void insertIntoUsers(Context context, String mEmail, String mPassword, String mUserName, String mPhoto) {
        DataBase mDbHelper = new DataBase(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntryUser.COLUMN_EMAIL_ENTRY_ID, mEmail);
        values.put(FeedReaderContract.FeedEntryUser.COLUMN_NAME, mUserName);
        values.put(FeedReaderContract.FeedEntryUser.COLUMN_PASS, mPassword);
        values.put(FeedReaderContract.FeedEntryUser.COLUMN_URL_PHOTO, mPhoto);

        // Insert the new row, returning the primary key value of the new row
        db.insert(
                FeedReaderContract.FeedEntryUser.TABLE_NAME, null,
                values);
        db.close();
        mDbHelper.close();
    }//insert users end

    //insert socres
    public static void insertIntoScores(Context context, String score, String emailuser, int level) {
        /*GARDASE A PUNTUACION NA BBDD*/
        DataBase mDbHelper = new DataBase(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        String date = CurrentDate.getDate();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntryScore.COLUMN_DATE, date);
        values.put(FeedReaderContract.FeedEntryScore.COLUMN_SCORE, score);
        values.put(FeedReaderContract.FeedEntryScore.COLUMN_EMAIL_USER, emailuser);
        values.put(FeedReaderContract.FeedEntryScore.COLUMN_LEVEL, level);

        if (level == 2 && selectRecord(context, emailuser, 9).size() == 0) {
            insertRecord(context, emailuser, date, 9);
        } else if (level == 3 && selectRecord(context, emailuser, 10).size() == 0) {
            insertRecord(context, emailuser, date, 10);
        }

        // Insert the new row, returning the primary key value of the new row
        db.insert(
                FeedReaderContract.FeedEntryScore.TABLE_NAME,
                null,
                values);
        db.close();
        mDbHelper.close();
    }//insert scores end

    //insert records names
    public static void insertIntoRecordsName(Context context, RecordsName recordsName) {
        /*GARDASE A PUNTUACION NA BBDD*/
        DataBase mDbHelper = new DataBase(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntryRecordName.COLUMN_ENTRY_ID, recordsName.getId_record());
        values.put(FeedReaderContract.FeedEntryRecordName.COLUMN_NAME, recordsName.getName_record());
        values.put(FeedReaderContract.FeedEntryRecordName.COLUMN_DESCRIPTION, recordsName.getDescription());

        // Insert the new row, returning the primary key value of the new row
        db.insert(
                FeedReaderContract.FeedEntryRecordName.TABLE_NAME,
                null,
                values);
        db.close();
        mDbHelper.close();
    }//insert records name end

    //insert the records
    public static boolean insertRecords(Context context, String email, int score) {
        int level = selectLevel(context, email);
        String date = CurrentDate.getDate();
        if (score >= 10 && selectRecord(context, email, 8).size() == 0) {
            insertRecord(context, email, date, 8);
        }
        switch (level) {
            case 1:
                if (score >= 30 && score <= 50 && selectRecord(context, email, 1).size() == 0) {
                    insertRecord(context, email, date, 1);
                }
                if (50 < score && score <= 90 && selectRecord(context, email, 2).size() == 0) {
                    insertRecord(context, email, date, 2);
                }
                if (90 < score && score <= 100 && selectRecord(context, email, 3).size() == 0) {
                    insertRecord(context, email, date, 3);
                }
                return true;
            case 2:
                if (score >= 50 && score <= 70 && selectRecord(context, email, 4).size() == 0) {
                    insertRecord(context, email, date, 4);
                }
                if (70 < score && score <= 90 && selectRecord(context, email, 5).size() == 0) {
                    insertRecord(context, email, date, 5);
                }
                return true;
            case 3:
                if (score >= 30 && score <= 50 && selectRecord(context, email, 6).size() == 0) {
                    insertRecord(context, email, date, 6);
                }
                if (50 < score && selectRecord(context, email, 7).size() == 0) {
                    insertRecord(context, email, date, 7);

                }
                return true;
        }
        return false;
    }

    public static boolean success = false;

    //insertRecords
    private static void insertRecord(Context context, String email, String date, int id) {

        DataBase mDbHelper = new DataBase(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntryRecords.COLUMN_EMAIL_USER_ENTRY_ID, email);
        values.put(FeedReaderContract.FeedEntryRecords.COLUMN_DATA, date);
        values.put(FeedReaderContract.FeedEntryRecords.COLUMN_RECORD_NAME, id);

        // Insert the new row, returning the primary key value of the new row
        db.insert(
                FeedReaderContract.FeedEntryRecords.TABLE_NAME,
                null,
                values);
        db.close();
        mDbHelper.close();
        success = true;
    }
    //END INSERT

    //SELECT
    //select general sentence
    public static Cursor selectSentence(Context context, String[] projectionRecived, String sortOrderRecived, String whereClauseREcived, String[] comparationClauseRecived, String tableName) {
        //LER DA BBDD
        DataBase mDbHelper = new DataBase(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = projectionRecived;
        // How you want the results sorted in the resulting Cursor
        String sortOrder = sortOrderRecived;
        String whereClause = whereClauseREcived;
        String[] comparationWhereClause = comparationClauseRecived;

        Cursor c = db.query(
                tableName,  // The table to query
                projection,                               // The columns to return
                whereClause,                              // The columns for the WHERE clause
                comparationWhereClause,                   // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );
        return c;
    }//end select general sentence
    // select last score
    public static String readLastScore(Context context, String email, int level) {
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        DataBase mDbHelper = new DataBase(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String[] projection = {FeedReaderContract.FeedEntryScore.COLUMN_SCORE};
        // How you want the results sorted in the resulting Cursor
        String sortOrder = FeedReaderContract.FeedEntryScore.COLUMN_DATE + " DESC";
        String whereClause = FeedReaderContract.FeedEntryScore.COLUMN_EMAIL_USER + " = ? AND " +
                FeedReaderContract.FeedEntryScore.COLUMN_LEVEL + " = ? ";
        String[] comparationWhereClause = {email, level + ""};
        Cursor c = db.query(
                FeedReaderContract.FeedEntryScore.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                whereClause,                              // The columns for the WHERE clause
                comparationWhereClause,                   // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );
        if (c.moveToFirst()) {
            return c.getString(0);
        }
        c.close();
        mDbHelper.close();
        return "0";
    }// end last score

    //select best score
    public static String readBestScore(Context context, String email, int level) {
        //LER DA BBDD
        DataBase mDbHelper = new DataBase(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {FeedReaderContract.FeedEntryScore.COLUMN_SCORE};
        // How you want the results sorted in the resulting Cursor
        String sortOrder = FeedReaderContract.FeedEntryScore.COLUMN_SCORE + " DESC";
        String whereClause =
                FeedReaderContract.FeedEntryScore.COLUMN_EMAIL_USER + " = ? AND " +
                        FeedReaderContract.FeedEntryScore.COLUMN_LEVEL + " = ? ";
        String[] comparationWhereClause = {email, level + ""};

        Cursor c = db.query(
                FeedReaderContract.FeedEntryScore.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                whereClause,                              // The columns for the WHERE clause
                comparationWhereClause,                   // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );
        if (c.moveToFirst()) {
            return c.getString(0);
        }
        c.close();
        mDbHelper.close();
        return "0";
    }//end best score


    //readBestScore by date
    public static String readBestScoreDate(Context context, String email, int level, String date) {
        //LER DA BBDD
        DataBase mDbHelper = new DataBase(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {FeedReaderContract.FeedEntryScore.COLUMN_SCORE};
        // How you want the results sorted in the resulting Cursor
        String sortOrder = FeedReaderContract.FeedEntryScore.COLUMN_SCORE + " DESC";
        String whereClause =
                FeedReaderContract.FeedEntryScore.COLUMN_EMAIL_USER + " = ? AND " +
                        FeedReaderContract.FeedEntryScore.COLUMN_LEVEL + " = ? AND " +
                        FeedReaderContract.FeedEntryScore.COLUMN_DATE + " <= ? ";
        String[] comparationWhereClause = {email, level + "", date};

        Cursor c = db.query(
                FeedReaderContract.FeedEntryScore.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                whereClause,                              // The columns for the WHERE clause
                comparationWhereClause,                   // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );
        if (c.moveToFirst()) {
            return c.getString(0);
        }
        c.close();
        mDbHelper.close();
        return "0";
    }//end


    public static ArrayList<Scores> readScores(Context context, String email, int level) {
        //LER DA BBDD
        DataBase mDbHelper = new DataBase(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                FeedReaderContract.FeedEntryScore.COLUMN_DATE,
                FeedReaderContract.FeedEntryScore.COLUMN_SCORE,
                FeedReaderContract.FeedEntryScore.COLUMN_LEVEL
        };

        // How you want the results sorted in the resulting Cursor
        String sortOrder = FeedReaderContract.FeedEntryScore.COLUMN_DATE + " DESC";
        String whereClause = FeedReaderContract.FeedEntryScore.COLUMN_EMAIL_USER + " = ? AND " +
                FeedReaderContract.FeedEntryScore.COLUMN_LEVEL + " = ? AND " +
                FeedReaderContract.FeedEntryScore.COLUMN_SCORE + " != 0 ";
        String[] comparationWhereClause = {email, level + ""};
        String tableName = FeedReaderContract.FeedEntryScore.TABLE_NAME;

        ArrayList<Scores> dataScores = new ArrayList<>();

        Cursor c = db.query(
                tableName,  // The table to query
                projection,                               // The columns to return
                whereClause,                              // The columns for the WHERE clause
                comparationWhereClause,                   // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                  // The sort order
        );
        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                Scores score = new Scores(CurrentDate.dateFormat(c.getString(0)), c.getInt(1), c.getInt(2));
                dataScores.add(score);
                c.moveToNext();
            }
        }
        c.close();
        mDbHelper.close();
        return dataScores;
    }

    public static boolean existsScore(Context context, String email, int level) {
        //LER DA BBDD
        DataBase mDbHelper = new DataBase(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                FeedReaderContract.FeedEntryScore.COLUMN_SCORE
        };

        // How you want the results sorted in the resulting Cursor
        String sortOrder = FeedReaderContract.FeedEntryScore.COLUMN_DATE + " DESC";
        String whereClause =
                FeedReaderContract.FeedEntryScore.COLUMN_EMAIL_USER + " = ? AND " +
                        FeedReaderContract.FeedEntryScore.COLUMN_LEVEL + " = ?";
        String[] comparationWhereClause = {email, level + ""};
        String tableName = FeedReaderContract.FeedEntryScore.TABLE_NAME;


        Cursor c = db.query(
                tableName,  // The table to query
                projection,                               // The columns to return
                whereClause,                              // The columns for the WHERE clause
                comparationWhereClause,                   // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                  // The sort order
        );

        boolean exists = (c.moveToFirst()) ? true : false;

        c.close();
        mDbHelper.close();
        return exists;
    }

    //select user
    public static ArrayList<String> readUser(Context context, String email) {
        //LER DA BBDD
        DataBase mDbHelper = new DataBase(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                FeedReaderContract.FeedEntryUser.COLUMN_EMAIL_ENTRY_ID,
                FeedReaderContract.FeedEntryUser.COLUMN_NAME,
                FeedReaderContract.FeedEntryUser.COLUMN_URL_PHOTO,
                FeedReaderContract.FeedEntryUser.COLUMN_PASS
        };

        // How you want the results sorted in the resulting Cursor
        String sortOrder = FeedReaderContract.FeedEntryUser.COLUMN_EMAIL_ENTRY_ID + " DESC";
        String whereClause = FeedReaderContract.FeedEntryUser.COLUMN_EMAIL_ENTRY_ID + " = ?";
        String[] comparationWhereClause = {email};
        String tableName = FeedReaderContract.FeedEntryUser.TABLE_NAME;

        ArrayList<String> dataUser = new ArrayList<>();

        Cursor c = db.query(
                tableName,  // The table to query
                projection,                               // The columns to return
                whereClause,                              // The columns for the WHERE clause
                comparationWhereClause,                   // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                  // The sort order
        );
        if (c.moveToFirst()) {
            dataUser.add(c.getString(0));
            dataUser.add(c.getString(1));
        }
        c.close();
        mDbHelper.close();
        return dataUser;
    }//end user

    //select level of user
    public static int selectLevel(Context context, String email) {

        int levelResult = 1;
        //LER DA BBDD
        DataBase mDbHelper = new DataBase(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                FeedReaderContract.FeedEntryScore.COLUMN_LEVEL
        };

        // How you want the results sorted in the resulting Cursor
        String sortOrder = FeedReaderContract.FeedEntryScore.COLUMN_LEVEL + " DESC";
        String whereClause = FeedReaderContract.FeedEntryScore.COLUMN_EMAIL_USER + " = ?";
        String[] comparativeWhereClause = {email};
        String tableName = FeedReaderContract.FeedEntryScore.TABLE_NAME;

        Cursor c = db.query(
                tableName,  // The table to query
                projection,                               // The columns to return
                whereClause,                              // The columns for the WHERE clause
                comparativeWhereClause,                   // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                  // The sort order
        );
        if (c.moveToFirst()) {
            levelResult = c.getInt(0);
        }
        c.close();
        mDbHelper.close();
        return levelResult;
    }//end level

    //select record
    public static ArrayList<String> selectRecord(Context context, String email, int id) {
        //LER DA BBDD
        DataBase mDbHelper = new DataBase(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                FeedReaderContract.FeedEntryRecords.COLUMN_EMAIL_USER_ENTRY_ID,
                FeedReaderContract.FeedEntryRecords.COLUMN_DATA,
                FeedReaderContract.FeedEntryRecords.COLUMN_RECORD_NAME
        };

        // How you want the results sorted in the resulting Cursor
        String sortOrder = FeedReaderContract.FeedEntryRecords.COLUMN_EMAIL_USER_ENTRY_ID + " DESC";
        String whereClause = FeedReaderContract.FeedEntryRecords.COLUMN_EMAIL_USER_ENTRY_ID + " = ? AND " +
                FeedReaderContract.FeedEntryRecords.COLUMN_RECORD_NAME + " = ?";
        String[] comparationWhereClause = {email, id + ""};
        String tableName = FeedReaderContract.FeedEntryRecords.TABLE_NAME;

        ArrayList<String> dataRecord = new ArrayList<>();

        Cursor c = db.query(
                tableName,  // The table to query
                projection,                               // The columns to return
                whereClause,                              // The columns for the WHERE clause
                comparationWhereClause,                   // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                  // The sort order
        );
        if (c.moveToFirst()) {
            dataRecord.add(c.getString(0));
            dataRecord.add(c.getString(1));
            dataRecord.add(c.getInt(2) + "");
        }
        c.close();
        mDbHelper.close();
        return dataRecord;
    }

    //select emails to autocomplete
    public static Cursor selectEmails(Context context) {
        //LER DA BBDD
        DataBase mDbHelper = new DataBase(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {FeedReaderContract.FeedEntryUser.COLUMN_EMAIL_ENTRY_ID};
        // How you want the results sorted in the resulting Cursor
        String sortOrder = null;
        String whereClause = null;
        String[] comparationWhereClause = null;

        Cursor c = db.query(
                true,
                FeedReaderContract.FeedEntryUser.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                whereClause,                              // The columns for the WHERE clause
                comparationWhereClause,                   // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder,                                 // The sort order
                null
        );
        return c;
    }

    //select records
    public static ArrayList<Records> readRecords(Context context, String email) {
        DataBase mDbHelper = new DataBase(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                FeedReaderContract.FeedEntryRecords.COLUMN_EMAIL_USER_ENTRY_ID,
                FeedReaderContract.FeedEntryRecords.COLUMN_DATA,
                FeedReaderContract.FeedEntryRecords.COLUMN_RECORD_NAME
        };

        // How you want the results sorted in the resulting Cursor
        String sortOrder = FeedReaderContract.FeedEntryRecords.COLUMN_RECORD_NAME + " ASC";
        String whereClause = FeedReaderContract.FeedEntryRecords.COLUMN_EMAIL_USER_ENTRY_ID + " = ? ";
        String[] comparationWhereClause = {email};
        String tableName = FeedReaderContract.FeedEntryRecords.TABLE_NAME;

        Cursor c = db.query(
                tableName,  // The table to query
                projection,                               // The columns to return
                whereClause,                              // The columns for the WHERE clause
                comparationWhereClause,                   // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                  // The sort order
        );

        ArrayList<Records> records = new ArrayList<>();
        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                Records record = new Records(c.getString(0), CurrentDate.dateFormat(c.getString(1)), c.getInt(2));
                records.add(record);
                c.moveToNext();
            }
        }
        c.close();
        mDbHelper.close();
        return records;
    }

    //selectRecordName
    public static ArrayList<RecordsName> readRecordsName(Context context) {
        DataBase mDbHelper = new DataBase(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                FeedReaderContract.FeedEntryRecordName.COLUMN_ENTRY_ID,
                FeedReaderContract.FeedEntryRecordName.COLUMN_NAME,
                FeedReaderContract.FeedEntryRecordName.COLUMN_DESCRIPTION
        };

        // How you want the results sorted in the resulting Cursor
        String sortOrder = FeedReaderContract.FeedEntryRecordName.COLUMN_ENTRY_ID + " ASC";
        String whereClause = null;
        String[] comparationWhereClause = null;
        String tableName = FeedReaderContract.FeedEntryRecordName.TABLE_NAME;

        Cursor c = db.query(
                tableName,  // The table to query
                projection,                               // The columns to return
                whereClause,                              // The columns for the WHERE clause
                comparationWhereClause,                   // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                  // The sort order
        );

        ArrayList<RecordsName> recordsName = new ArrayList<>();
        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                RecordsName recordName = new RecordsName(c.getInt(0), c.getString(1), c.getString(2));
                recordsName.add(recordName);
                c.moveToNext();
            }
        }
        c.close();
        mDbHelper.close();
        return recordsName;
    }

    public static ArrayList<Date> readDates(Context context) {
        DataBase mDbHelper = new DataBase(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                FeedReaderContract.FeedEntryScore.COLUMN_DATE
        };

        // How you want the results sorted in the resulting Cursor
        String sortOrder = FeedReaderContract.FeedEntryScore.COLUMN_DATE + " DESC";
        String whereClause = null;
        String[] comparationWhereClause = null;
        String tableName = FeedReaderContract.FeedEntryScore.TABLE_NAME;

        Cursor c = db.query(
                true,
                tableName,
                projection,
                whereClause,
                comparationWhereClause,
                null,
                null,
                sortOrder,
                null
        );

        ArrayList<Date> dates = new ArrayList<>();
        if (c.moveToFirst()) {
            dates.add(CurrentDate.dateFormat(c.getString(0)));
        }
        c.close();
        mDbHelper.close();
        return dates;
    }

    //select total score
    public static String readTotalScores(Context context, String email, int level) {
        DataBase mDbHelper = new DataBase(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                FeedReaderContract.FeedEntryScore.COLUMN_SCORE
        };

        // How you want the results sorted in the resulting Cursor
        String sortOrder = FeedReaderContract.FeedEntryScore.COLUMN_DATE + " DESC";
        String whereClause = "";
        switch (level) {
            case 1:
                whereClause = FeedReaderContract.FeedEntryScore.COLUMN_SCORE + " > 70 AND level = 1";
                break;
            case 2:
                whereClause = FeedReaderContract.FeedEntryScore.COLUMN_SCORE + " > 60 AND level = 2";
                break;
            case 3:
                whereClause = FeedReaderContract.FeedEntryScore.COLUMN_SCORE + " > 30 AND level = 3";
                break;
        }
        String[] comparationWhereClause = null;
        String tableName = FeedReaderContract.FeedEntryScore.TABLE_NAME;

        Cursor c = db.query(
                tableName,
                projection,
                whereClause,
                comparationWhereClause,
                null,
                null,
                sortOrder
        );

        int totalscores = 0;
        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                totalscores = totalscores + c.getInt(0);
                c.moveToNext();
            }
        }
        c.close();

        mDbHelper.close();
        return totalscores + "";
    }
    //end total score

    //END SELECT


    //delete querys
    public static boolean deleteScores(Context context, String email) {
        boolean deleteCorrect = false;
        DataBase mDbHelper = new DataBase(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        /// Define 'where' part of query.
        String selection = FeedReaderContract.FeedEntryScore.COLUMN_EMAIL_USER + " = ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = {String.valueOf(email)};
        // Issue SQL statement.

        String table_name = FeedReaderContract.FeedEntryScore.TABLE_NAME;
        int count = db.delete(table_name, selection, selectionArgs);
        if (count != 0) {
            deleteCorrect = true;
        }
        mDbHelper.close();
        return deleteCorrect;
    }

    public static boolean deleteRecords(Context context, String email) {
        DataBase mDbHelper = new DataBase(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        /// Define 'where' part of query.
        String selection = FeedReaderContract.FeedEntryRecords.COLUMN_EMAIL_USER_ENTRY_ID + " = ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = {String.valueOf(email)};
        // Issue SQL statement.

        String table_name = FeedReaderContract.FeedEntryRecords.TABLE_NAME;
        int count = db.delete(table_name, selection, selectionArgs);
        boolean deleteCorrect = (count != 0) ? true : false;
        mDbHelper.close();
        return deleteCorrect;
    }

    //END DELETE

}
