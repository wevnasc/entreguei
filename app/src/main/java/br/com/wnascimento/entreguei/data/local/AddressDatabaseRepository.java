package br.com.wnascimento.entreguei.data.local;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import javax.inject.Inject;

import br.com.wnascimento.entreguei.data.local.PersistenceContract.AddressEntry;
import br.com.wnascimento.entreguei.data.local.PersistenceContract.UserAddressEntry;
import br.com.wnascimento.entreguei.features.address.Address;
import br.com.wnascimento.entreguei.features.address.search.AddressLocalRepository;
import br.com.wnascimento.entreguei.shared.preferences.ApplicationPreferencesInterface;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.Flowable;


public class AddressDatabaseRepository implements AddressLocalRepository {

    private final DatabaseHelper databaseHelper;

    private final ApplicationPreferencesInterface applicationPreferences;

    @Inject
    public AddressDatabaseRepository(DatabaseHelper databaseHelper, ApplicationPreferencesInterface applicationPreferences) {
        this.databaseHelper = databaseHelper;
        this.applicationPreferences = applicationPreferences;
    }

    @Override
    public Completable save(Address address) {
        return Completable.fromAction(() -> {

            SQLiteDatabase db = databaseHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(AddressEntry.COLUMN_NAME_CEP, address.getCep());
            values.put(AddressEntry.COLUMN_NAME_CITY, address.getCity());
            values.put(AddressEntry.COLUMN_NAME_COMPLEMENT, address.getComplement());
            values.put(AddressEntry.COLUMN_NAME_NEIGHBORHOOD, address.getNeighborhood());
            values.put(AddressEntry.COLUMN_NAME_STATE, address.getState());
            values.put(AddressEntry.COLUMN_NAME_STREET, address.getStreet());

            db.insert(AddressEntry.TABLE_NAME, null, values);

            values = new ContentValues();
            values.put(UserAddressEntry.COLUMN_NAME_ADDRESS_CEP, address.getCep());
            values.put(UserAddressEntry.COLUMN_NAME_USER_ID, applicationPreferences.getCurrentUser().getId());

            db.insertOrThrow(UserAddressEntry.TABLE_NAME, null, values);

            db.close();

        });
    }

    @Override
    public Flowable<Address> getAddresses() {
        return Flowable.create(e -> {
            SQLiteDatabase db = databaseHelper.getReadableDatabase();

            String query = "SELECT " + AddressEntry.TABLE_NAME + ".* FROM " + AddressEntry.TABLE_NAME +
                    " INNER JOIN " + UserAddressEntry.TABLE_NAME +
                    " ON " + AddressEntry.TABLE_NAME + "." + AddressEntry.COLUMN_NAME_CEP + " = " + UserAddressEntry.TABLE_NAME + "." + UserAddressEntry.COLUMN_NAME_ADDRESS_CEP +
                    " WHERE " + UserAddressEntry.COLUMN_NAME_USER_ID + " = ?";

            String [] args = {String.valueOf(applicationPreferences.getCurrentUser().getId())};

            Cursor cursor = db.rawQuery(query, args);

            if (cursor != null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    Address address = new Address(
                            cursor.getString((cursor.getColumnIndexOrThrow(AddressEntry.COLUMN_NAME_CEP))),
                            cursor.getString(cursor.getColumnIndexOrThrow(AddressEntry.COLUMN_NAME_STREET)),
                            cursor.getString(cursor.getColumnIndexOrThrow(AddressEntry.COLUMN_NAME_NEIGHBORHOOD)),
                            cursor.getString(cursor.getColumnIndexOrThrow(AddressEntry.COLUMN_NAME_CITY)),
                            cursor.getString(cursor.getColumnIndexOrThrow(AddressEntry.COLUMN_NAME_STATE)),
                            cursor.getString(cursor.getColumnIndexOrThrow(AddressEntry.COLUMN_NAME_COMPLEMENT))
                    );
                    e.onNext(address);
                }
            }

            if (cursor != null) {
                cursor.close();
            }

            e.onComplete();

        }, BackpressureStrategy.BUFFER);
    }

    @Override
    public Completable removeAddress(String cep) {

        return Completable.fromAction(() -> {
            SQLiteDatabase db = databaseHelper.getWritableDatabase();

            String [] args = {cep, String.valueOf(applicationPreferences.getCurrentUser().getId())};

            db.delete(UserAddressEntry.TABLE_NAME, UserAddressEntry.COLUMN_NAME_ADDRESS_CEP + " = ?" +
                    " AND " + UserAddressEntry.COLUMN_NAME_USER_ID + " = ?", args);

        });
    }
}
