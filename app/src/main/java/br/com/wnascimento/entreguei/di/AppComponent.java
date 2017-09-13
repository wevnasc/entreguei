package br.com.wnascimento.entreguei.di;


import javax.inject.Singleton;

import br.com.wnascimento.entreguei.MainApplication;
import br.com.wnascimento.entreguei.data.RepositoryModule;
import br.com.wnascimento.entreguei.shared.scheduler.SchedulerModule;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ActivityBuilder.class,
        AppModule.class,
        RepositoryModule.class,
        SchedulerModule.class
})

public interface AppComponent extends AndroidInjector<MainApplication> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<MainApplication> {
    }

}
