package br.com.wnascimento.entreguei.data.local;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import javax.inject.Inject;

import br.com.wnascimento.entreguei.features.address.search.Address;
import br.com.wnascimento.entreguei.features.address.search.AddressLocalRepository;
import br.com.wnascimento.entreguei.shared.preferences.ApplicationPreferencesInterface;
import io.reactivex.Completable;


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
            values.put(PersistenceContract.AddressEntry.COLUMN_NAME_CEP, address.getCepToInt());
            values.put(PersistenceContract.AddressEntry.COLUMN_NAME_CITY, address.getCity());
            values.put(PersistenceContract.AddressEntry.COLUMN_NAME_COMPLEMENT, address.getComplement());
            values.put(PersistenceContract.AddressEntry.COLUMN_NAME_NEIGHBORHOOD, address.getNeighborhood());
            values.put(PersistenceContract.AddressEntry.COLUMN_NAME_STATE, address.getState());
            values.put(PersistenceContract.AddressEntry.COLUMN_NAME_STREET, address.getStreet());

            db.insert(PersistenceContract.AddressEntry.TABLE_NAME, null, values);

            values = new ContentValues();
            values.put(PersistenceContract.UserAddressEntry.COLUMN_NAME_ADDRESS_CEP, address.getCepToInt());
            values.put(PersistenceContract.UserAddressEntry.COLUMN_NAME_USER_ID, applicationPreferences.getCurrentUser().getId());

            db.insertOrThrow(PersistenceContract.UserAddressEntry.TABLE_NAME, null, values);

            db.close();

        });
    }
}
