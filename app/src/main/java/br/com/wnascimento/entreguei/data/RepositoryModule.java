package br.com.wnascimento.entreguei.data;


import android.content.Context;

import javax.inject.Singleton;

import br.com.wnascimento.entreguei.data.local.DatabaseHelper;
import br.com.wnascimento.entreguei.data.local.MotoboyDatabaseRepository;
import br.com.wnascimento.entreguei.features.authentication.MotoboyRepository;
import dagger.Module;
import dagger.Provides;


@Module
public class RepositoryModule {

    @Provides
    @Singleton
    DatabaseHelper providerDatabaseHelper(Context context) {
        return new DatabaseHelper(context);
    }

    @Provides
    @Singleton
    MotoboyRepository providerMotoboyRepository(DatabaseHelper databaseHelper) {
        return new MotoboyDatabaseRepository(databaseHelper);
    }

}
