package br.com.wnascimento.entreguei.features.address.search;


import br.com.wnascimento.entreguei.features.address.Address;
import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface AddressLocalRepository {

    Completable save(Address address);

    Flowable<Address> getAddresses();
}
