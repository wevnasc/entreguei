package br.com.wnascimento.entreguei.data;


import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import javax.inject.Singleton;

import br.com.wnascimento.entreguei.R;
import br.com.wnascimento.entreguei.data.local.AddressDatabaseRepository;
import br.com.wnascimento.entreguei.data.local.DatabaseHelper;
import br.com.wnascimento.entreguei.data.local.UserDatabaseLocalRepository;
import br.com.wnascimento.entreguei.data.preferences.ApplicationPreferences;
import br.com.wnascimento.entreguei.data.remote.AddressWebServiceRepository;
import br.com.wnascimento.entreguei.features.address.search.AddressLocalRepository;
import br.com.wnascimento.entreguei.features.address.search.AddressRemoteRepository;
import br.com.wnascimento.entreguei.features.authentication.UserLocalRepository;
import br.com.wnascimento.entreguei.shared.preferences.ApplicationPreferencesInterface;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;


@Module
public class DataModule {

    @Provides
    @Singleton
    DatabaseHelper providerDatabaseHelper(Context context) {
        return new DatabaseHelper(context);
    }

    @Provides
    @Singleton
    Gson providerGson() {
        return new Gson();
    }

    @Provides
    @Singleton
    OkHttpClient providerOkHttpClient() {
        return new OkHttpClient();
    }

    @Provides
    @Singleton
    ApplicationPreferencesInterface providerApplicationPreferences(Context context) {
        return new ApplicationPreferences(context);
    }

    @Provides
    @Singleton
    UserLocalRepository providerDatabaseUserRepository(DatabaseHelper databaseHelper) {
        return new UserDatabaseLocalRepository(databaseHelper);
    }

    @Provides
    @Singleton
    AddressRemoteRepository providerWebServiceAddressRepository(OkHttpClient client, Gson gson) {
        return new AddressWebServiceRepository(client, gson);
    }

    @Provides
    @Singleton
    AddressLocalRepository providerDatabaseAddressRepository(DatabaseHelper databaseHelper, ApplicationPreferencesInterface applicationPreferencesInterface) {
        return new AddressDatabaseRepository(databaseHelper, applicationPreferencesInterface);
    }
}
