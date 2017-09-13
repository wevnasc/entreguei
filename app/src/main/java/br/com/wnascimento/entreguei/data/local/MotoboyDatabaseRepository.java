package br.com.wnascimento.entreguei.data.local;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import javax.inject.Inject;

import br.com.wnascimento.entreguei.data.local.MotoboyPersistenceContract.MotoboyEntry;
import br.com.wnascimento.entreguei.features.authentication.Motoboy;
import br.com.wnascimento.entreguei.features.authentication.MotoboyRepository;
import io.reactivex.Completable;

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
    public Completable authenticate(Motoboy motoboy) {
        return null;
    }

}
