package br.com.wnascimento.entreguei.data.local;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import javax.inject.Inject;

import br.com.wnascimento.entreguei.shared.exception.MotoboyNotFoundException;
import br.com.wnascimento.entreguei.data.local.PersistenceContract.UserEntry;
import br.com.wnascimento.entreguei.features.authentication.User;
import br.com.wnascimento.entreguei.features.authentication.UserLocalRepository;
import io.reactivex.Completable;
import io.reactivex.Maybe;

public class UserDatabaseLocalRepository implements UserLocalRepository {

    private final DatabaseHelper databaseHelper;

    @Inject
    public UserDatabaseLocalRepository(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    @Override
    public Completable registerNew(User user) {
        return Completable.fromAction(() -> {

            SQLiteDatabase db = databaseHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(UserEntry.COLUMN_NAME_EMAIL, user.getEmail());
            values.put(UserEntry.COLUMN_NAME_PASSWORD, user.getPassword());

            db.insertOrThrow(UserEntry.TABLE_NAME, null, values);
            db.close();

        });
    }

    @Override
    public Maybe<User> getUserByEmailAndPassword(String email, String password) {
        return Maybe.create(e -> {
            User user = null;
            SQLiteDatabase db = databaseHelper.getReadableDatabase();

            String[] columns = {
                    UserEntry.COLUMN_NAME_ID,
                    UserEntry.COLUMN_NAME_EMAIL,
                    UserEntry.COLUMN_NAME_PASSWORD
            };

            String condition = UserEntry.COLUMN_NAME_EMAIL + " = ? AND " +
                    UserEntry.COLUMN_NAME_PASSWORD + " = ? ";

            String [] values = {email, password};

            Cursor cursor = db.query(UserEntry.TABLE_NAME, columns, condition, values, null, null, null);

            if(cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                user = new User(
                        (long) cursor.getInt(cursor.getColumnIndexOrThrow(UserEntry.COLUMN_NAME_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(UserEntry.COLUMN_NAME_EMAIL)),
                        cursor.getString(cursor.getColumnIndexOrThrow(UserEntry.COLUMN_NAME_PASSWORD))
                );
            }

            if (cursor != null) {
                cursor.close();
            }

            if(user != null) {
                e.onSuccess(user);
                e.onComplete();
            } else {
                e.onError(new MotoboyNotFoundException("user not found in database!"));
            }

        });
    }

}
