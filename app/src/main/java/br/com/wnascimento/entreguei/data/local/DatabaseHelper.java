package br.com.wnascimento.entreguei.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "entreguei.db";

    private static final String TEXT_TYPE = " TEXT";

    private static final String INTEGER_TYPE = " INTEGER";

    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_TABLE_MOTOBOY =
            "CREATE TABLE " + PersistenceContract.UserEntry.TABLE_NAME + " (" +
                    PersistenceContract.UserEntry.COLUMN_NAME_ID + INTEGER_TYPE + " PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
                    PersistenceContract.UserEntry.COLUMN_NAME_EMAIL + TEXT_TYPE + " NOT NULL " + COMMA_SEP +
                    PersistenceContract.UserEntry.COLUMN_NAME_PASSWORD + TEXT_TYPE + COMMA_SEP +
                    " UNIQUE(" + PersistenceContract.UserEntry.COLUMN_NAME_EMAIL + ")" +
                    " )";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_MOTOBOY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
