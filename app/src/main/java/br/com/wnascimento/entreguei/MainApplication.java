package br.com.wnascimento.entreguei;

import br.com.wnascimento.entreguei.di.DaggerAppComponent;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class MainApplication extends DaggerApplication {

    @Override
    protected AndroidInjector<MainApplication> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }

}
