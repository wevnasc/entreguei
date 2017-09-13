package br.com.wnascimento.entreguei.features.authentication;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class AuthenticationModule {

    @Binds
    abstract AuthenticationContract.View providerRegisterView(AuthenticationActivity authenticationActivity);

    @Binds
    abstract AuthenticationContract.Presenter providerRegisterPresenter(AuthenticationPresenter authenticationPresenter);

}
