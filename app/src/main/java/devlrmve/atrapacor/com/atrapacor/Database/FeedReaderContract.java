package devlrmve.atrapacor.com.atrapacor.Database;

import android.provider.BaseColumns;

/**
 * Created by Marco on 13/12/2015.
 */
public class FeedReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public FeedReaderContract() {
    }

    /* Inner class that defines the table contents */
    public static abstract class FeedEntryUser implements BaseColumns {
        public static final String TABLE_NAME = "USERS";
        public static final String COLUMN_EMAIL_ENTRY_ID = "EMAIL";
        public static final String COLUMN_NAME = "NAME";
        public static final String COLUMN_URL_PHOTO = "PHOTO";
        public static final String COLUMN_PASS = "PASSWORD";
    }
    public static abstract class FeedEntryScore implements BaseColumns {
        public static final String TABLE_NAME = "SCORE";
        public static final String COLUMN_ENTRY_ID = "ID";
        public static final String COLUMN_DATE = "DATA";
        public static final String COLUMN_SCORE = "PUNTUATION";
        public static final String COLUMN_EMAIL_USER = "EMAIL_USERS";
        public static final String COLUMN_LEVEL = "LEVEL";
    }
    public static abstract class FeedEntryRecordName implements BaseColumns {
        public static final String TABLE_NAME = "RECORD_NAME";
        public static final String COLUMN_ENTRY_ID = "ID";
        public static final String COLUMN_NAME = "NAME";
        public static final String COLUMN_DESCRIPTION = "DESCRIPTION";
    }
    public static abstract class FeedEntryRecords implements BaseColumns {
        public static final String TABLE_NAME = "RECORDS";
        public static final String COLUMN_EMAIL_USER_ENTRY_ID = "EMAIL_USERS";
        public static final String COLUMN_DATA = "DATA";
        public static final String COLUMN_RECORD_NAME = "ID_RECORD_NAME";
    }
}
