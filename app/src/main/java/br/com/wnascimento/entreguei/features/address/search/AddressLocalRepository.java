package br.com.wnascimento.entreguei.features.address.search;


import io.reactivex.Completable;

public interface AddressLocalRepository {

    Completable save(Address address);

}
