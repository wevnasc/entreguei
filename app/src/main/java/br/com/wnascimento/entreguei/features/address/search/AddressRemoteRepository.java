package br.com.wnascimento.entreguei.features.address.search;


import io.reactivex.Single;

public interface AddressRemoteRepository {

    Single<Address> getAddressByCep(String s);

}
