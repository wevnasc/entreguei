package br.com.wnascimento.entreguei.features.address.search;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

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
    public void onClickSearch_showAddress() {
        searchAddress(Single.just(mock(Address.class)));

        verify(searchAddressView).showProgress();
        verify(searchAddressView).hideProgress();
        verify(searchAddressView).showAddressInformation(any(Address.class));
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

}
