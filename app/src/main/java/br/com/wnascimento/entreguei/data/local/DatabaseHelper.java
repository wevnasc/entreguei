package br.com.wnascimento.entreguei.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.wnascimento.entreguei.data.local.PersistenceContract.AddressEntry;
import br.com.wnascimento.entreguei.data.local.PersistenceContract.UserAddressEntry;
import br.com.wnascimento.entreguei.data.local.PersistenceContract.UserEntry;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "entreguei.db";

    private static final String TEXT_TYPE = " TEXT";

    private static final String INTEGER_TYPE = " INTEGER";

    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_TABLE_USER =
            "CREATE TABLE " + UserEntry.TABLE_NAME + " (" +
                    UserEntry.COLUMN_NAME_ID + INTEGER_TYPE + " PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
                    UserEntry.COLUMN_NAME_EMAIL + TEXT_TYPE + " NOT NULL " + COMMA_SEP +
                    UserEntry.COLUMN_NAME_PASSWORD + TEXT_TYPE + COMMA_SEP +
                    " UNIQUE(" + UserEntry.COLUMN_NAME_EMAIL + ")" +
                    " )";

    private static final String SQL_CREATE_TABLE_ADDRESS =
            "CREATE TABLE " + AddressEntry.TABLE_NAME + " (" +
                    AddressEntry.COLUMN_NAME_CEP + INTEGER_TYPE + " PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
                    AddressEntry.COLUMN_NAME_STREET + TEXT_TYPE + COMMA_SEP +
                    AddressEntry.COLUMN_NAME_NEIGHBORHOOD + TEXT_TYPE + COMMA_SEP +
                    AddressEntry.COLUMN_NAME_CITY + TEXT_TYPE + COMMA_SEP +
                    AddressEntry.COLUMN_NAME_STATE + TEXT_TYPE + COMMA_SEP +
                    AddressEntry.COLUMN_NAME_COMPLEMENT + TEXT_TYPE + COMMA_SEP +
                    " UNIQUE(" + AddressEntry.COLUMN_NAME_CEP + ")" +
                    " )";

    private static final String SQL_CREATE_TABLE_USER_ADDRESS =
            "CREATE TABLE " + UserAddressEntry.TABLE_NAME + " (" +
                    UserAddressEntry.COLUMN_NAME_USER_ID + INTEGER_TYPE  + COMMA_SEP +
                    UserAddressEntry.COLUMN_NAME_ADDRESS_CEP + INTEGER_TYPE + COMMA_SEP +
                    "PRIMARY KEY (" + UserAddressEntry.COLUMN_NAME_USER_ID + ", " + UserAddressEntry.COLUMN_NAME_ADDRESS_CEP + ")," +
                    "FOREIGN KEY (" + UserAddressEntry.COLUMN_NAME_USER_ID + ") " +
                    "REFERENCES " + UserEntry.TABLE_NAME  + " ( " + UserEntry.COLUMN_NAME_ID + " )" + COMMA_SEP +
                    "FOREIGN KEY (" + UserAddressEntry.COLUMN_NAME_ADDRESS_CEP + ") " +
                    "REFERENCES " + AddressEntry.TABLE_NAME  + " (" + AddressEntry.COLUMN_NAME_CEP + ")" +
                    " )";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_USER);
        db.execSQL(SQL_CREATE_TABLE_ADDRESS);
        db.execSQL(SQL_CREATE_TABLE_USER_ADDRESS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
