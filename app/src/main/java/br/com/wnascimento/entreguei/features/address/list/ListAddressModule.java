package br.com.wnascimento.entreguei.features.address.list;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ListAddressModule {

    @Binds
    abstract ListAddressesContract.View providerSearchAddressesView(ListAddressesActivity listAddressesActivity);

    @Binds
    abstract ListAddressesContract.Presenter providerSearchAddressesPresenter(ListAddressesPresenter listAddressesPresenter);


}
