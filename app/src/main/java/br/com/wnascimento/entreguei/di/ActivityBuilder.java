package br.com.wnascimento.entreguei.di;

import br.com.wnascimento.entreguei.features.authentication.AuthenticationActivity;
import br.com.wnascimento.entreguei.features.authentication.AuthenticationModule;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = AuthenticationModule.class)
    abstract AuthenticationActivity bindRegisterActivity();

}
