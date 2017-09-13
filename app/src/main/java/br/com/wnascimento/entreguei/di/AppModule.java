package br.com.wnascimento.entreguei.di;

import android.content.Context;

import br.com.wnascimento.entreguei.MainApplication;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    Context provideContext(MainApplication application) {
        return application.getApplicationContext();
    }
}
