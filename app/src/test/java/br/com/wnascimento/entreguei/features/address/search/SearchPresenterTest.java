package br.com.wnascimento.entreguei.features.address.search;

import android.support.annotation.NonNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import br.com.wnascimento.entreguei.features.address.Address;
import io.reactivex.Completable;
import io.reactivex.Single;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class SearchPresenterTest {

    private static final String CEP_TEST = "0000000";

    @Mock
    private SearchAddressesContract.View searchAddressView;

    @Mock
    private SearchAddressUseCase searchAddressUseCase;

    @Mock
    private SaveAddressUseCase saveAddressUseCase;


    private SearchAddressesContract.Presenter searchAddressPresenter;


    @Before
    public void setup() {
        initMocks(this);
        searchAddressPresenter = new SearchAddressesPresenter(searchAddressView, searchAddressUseCase, saveAddressUseCase);
    }


    @Test
    public void onClickSearch_nullAddressShowAddressNotFound() {
        searchAddress(Single.just(mock(Address.class)));

        verify(searchAddressView).showProgress();
        verify(searchAddressView).hideProgress();
        verify(searchAddressView).showErrorAddressNotFound();
    }

    @Test
    public void onClickSearch_ShowAddress() {
        Address address = getAddress();

        searchAddress(Single.just(address));

        verify(searchAddressView).showProgress();
        verify(searchAddressView).hideProgress();
        verify(searchAddressView).showAddressInformation(address);
    }



    @Test
    public void onClickSearch_showErrorAddressNotFound() {
        searchAddress(Single.error(mock(Exception.class)));

        verify(searchAddressView).showProgress();
        verify(searchAddressView).hideProgress();
        verify(searchAddressView).showErrorAddressNotFound();
    }

    private void searchAddress(Single<Address> just) {
        when(searchAddressUseCase.execute(any(SearchAddressUseCase.Request.class)))
                .thenReturn(just);

        searchAddressPresenter.searchAddress(CEP_TEST);
    }

    @Test
    public void onClickSave_saveAddressWithSuccess() {

        when(saveAddressUseCase.execute(any(SaveAddressUseCase.Request.class)))
                .thenReturn(Completable.complete());

        searchAddressPresenter.saveAddress(mock(Address.class));

        verify(searchAddressView).showProgress();
        verify(searchAddressView).hideProgress();
        verify(searchAddressView).notifySaveSuccess();

    }

    @Test
    public void onClickSave_showMessageError() {

        when(saveAddressUseCase.execute(any(SaveAddressUseCase.Request.class)))
                .thenReturn(Completable.error(mock(Exception.class)));

        searchAddressPresenter.saveAddress(mock(Address.class));

        verify(searchAddressView).showProgress();
        verify(searchAddressView).hideProgress();
        verify(searchAddressView).notifySaveError();

    }

    @NonNull
    private Address getAddress() {
        return new Address(CEP_TEST, "STREET",
                "NEIGHBORHOOD", "CITY", "STATE", "COMPLEMENT");
    }

}
