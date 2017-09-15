package br.com.wnascimento.entreguei.features.address.search;


import dagger.Binds;
import dagger.Module;

@Module
public abstract class SearchAddressesModule {

    @Binds
    abstract SearchAddressesContract.View providerSearchAddressesView(SearchAddressActivity searchAddressActivity);

    @Binds
    abstract SearchAddressesContract.Presenter providerSearchAddressesPresenter(SearchAddressesPresenter searchAddressesPresenter);

}
