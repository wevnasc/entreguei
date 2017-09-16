package br.com.wnascimento.entreguei.features.address.search;


import br.com.wnascimento.entreguei.features.address.Address;
import io.reactivex.Single;

public interface AddressRemoteRepository {

    Single<Address> getAddressByCep(String s);

}
