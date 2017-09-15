package br.com.wnascimento.entreguei.data;


import android.content.Context;

import com.google.gson.Gson;

import javax.inject.Singleton;

import br.com.wnascimento.entreguei.data.local.DatabaseHelper;
import br.com.wnascimento.entreguei.data.local.UserDatabaseLocalRepository;
import br.com.wnascimento.entreguei.data.remote.AddressWebServiceRepository;
import br.com.wnascimento.entreguei.features.address.search.AddressRemoteRepository;
import br.com.wnascimento.entreguei.features.authentication.UserLocalRepository;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;


@Module
public class RepositoryModule {

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
    UserLocalRepository providerUserRepository(DatabaseHelper databaseHelper) {
        return new UserDatabaseLocalRepository(databaseHelper);
    }

    @Provides
    @Singleton
    AddressRemoteRepository providerAddressRepository(OkHttpClient client, Gson gson) {
        return new AddressWebServiceRepository(client, gson);
    }
}
