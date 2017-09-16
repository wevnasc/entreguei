package br.com.wnascimento.entreguei.di;

import br.com.wnascimento.entreguei.features.address.list.ListAddressModule;
import br.com.wnascimento.entreguei.features.address.list.ListAddressesActivity;
import br.com.wnascimento.entreguei.features.address.search.SearchAddressActivity;
import br.com.wnascimento.entreguei.features.address.search.SearchAddressesModule;
import br.com.wnascimento.entreguei.features.authentication.AuthenticationActivity;
import br.com.wnascimento.entreguei.features.authentication.AuthenticationModule;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = AuthenticationModule.class)
    abstract AuthenticationActivity bindRegisterActivity();

    @ContributesAndroidInjector(modules = SearchAddressesModule.class)
    abstract SearchAddressActivity bindSearchAddressesActivity();

    @ContributesAndroidInjector(modules = ListAddressModule.class)
    abstract ListAddressesActivity bindListAddressesActivity();

}
