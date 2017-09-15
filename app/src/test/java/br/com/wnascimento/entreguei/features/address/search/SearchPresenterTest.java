package br.com.wnascimento.entreguei.features.address.search;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

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


    private SearchAddressesContract.Presenter searchAddressPresenter;


    @Before
    public void setup() {
        initMocks(this);
        searchAddressPresenter = new SearchAddressesPresenter(searchAddressView, searchAddressUseCase);
    }


    @Test
    public void onClickSearch_showAddress() {
        when(searchAddressUseCase.execute(any(SearchAddressUseCase.Request.class)))
                .thenReturn(Single.just(mock(Address.class)));

        searchAddressPresenter.searchAddress(CEP_TEST);

        verify(searchAddressView).showProgress();
        verify(searchAddressView).hideProgress();
        verify(searchAddressView).showAddressInformation(any(Address.class));
    }

    @Test
    public void onClickSearch_showErrorAddressNotFound() {
        when(searchAddressUseCase.execute(any(SearchAddressUseCase.Request.class)))
                .thenReturn(Single.error(mock(Exception.class)));

        searchAddressPresenter.searchAddress(CEP_TEST);

        verify(searchAddressView).showProgress();
        verify(searchAddressView).hideProgress();
        verify(searchAddressView).showErrorAddressNotFound();
    }


}
