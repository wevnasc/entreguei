package br.com.wnascimento.entreguei.data.local;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import javax.inject.Inject;

import br.com.wnascimento.entreguei.data.exception.MotoboyNotFoundException;
import br.com.wnascimento.entreguei.data.local.MotoboyPersistenceContract.MotoboyEntry;
import br.com.wnascimento.entreguei.features.authentication.Motoboy;
import br.com.wnascimento.entreguei.features.authentication.MotoboyRepository;
import io.reactivex.Completable;
import io.reactivex.Maybe;

public class MotoboyDatabaseRepository implements MotoboyRepository {

    private final DatabaseHelper databaseHelper;

    @Inject
    public MotoboyDatabaseRepository(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    @Override
    public Completable registerNew(Motoboy motoboy) {
        return Completable.fromAction(() -> {

            SQLiteDatabase db = databaseHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(MotoboyEntry.COLUMN_NAME_EMAIL, motoboy.getEmail());
            values.put(MotoboyEntry.COLUMN_NAME_PASSWORD, motoboy.getPassword());

            db.insertOrThrow(MotoboyEntry.TABLE_NAME, null, values);
            db.close();

        });
    }

    @Override
    public Maybe<Motoboy> getMotoboyByEmailAndPassword(String email, String password) {
        return Maybe.create(e -> {
            Motoboy motoboy = null;
            SQLiteDatabase db = databaseHelper.getReadableDatabase();

            String[] columns = {
                    MotoboyEntry.COLUMN_NAME_ID,
                    MotoboyEntry.COLUMN_NAME_EMAIL,
                    MotoboyEntry.COLUMN_NAME_PASSWORD
            };

            String condition = MotoboyEntry.COLUMN_NAME_EMAIL + " = ? AND " +
                    MotoboyEntry.COLUMN_NAME_PASSWORD + " = ? ";

            String [] values = {email, password};

            Cursor cursor = db.query(MotoboyEntry.TABLE_NAME, columns, condition, values, null, null, null);

            if(cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                motoboy = new Motoboy(
                        (long) cursor.getInt(cursor.getColumnIndexOrThrow(MotoboyEntry.COLUMN_NAME_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(MotoboyEntry.COLUMN_NAME_EMAIL)),
                        cursor.getString(cursor.getColumnIndexOrThrow(MotoboyEntry.COLUMN_NAME_PASSWORD))
                );
            }

            if (cursor != null) {
                cursor.close();
            }

            if(motoboy != null) {
                e.onSuccess(motoboy);
                e.onComplete();
            } else {
                e.onError(new MotoboyNotFoundException("motoboy not found in database!"));
            }

        });
    }

}
