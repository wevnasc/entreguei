package br.com.wnascimento.entreguei.features.address.list;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

import br.com.wnascimento.entreguei.features.address.Address;
import io.reactivex.Single;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ListAddressPresenterTest {

    @Mock
    private ListAddressesUseCase listAddressesUseCase;

    @Mock
    private ListAddressesContract.View listAddressesView;

    @Mock
    private List<Address> addressList;


    private ListAddressesContract.Presenter listAddressesPresenter;


    @Before
    public void setup() {
        initMocks(this);
        listAddressesPresenter = new ListAddressesPresenter(listAddressesView, listAddressesUseCase);
    }

    @Test
    public void listAllAddress_showListAddresses() {

        listAddresses(Single.just(addressList));

        verify(listAddressesView).showProgress();
        verify(listAddressesView).hideProgress();
        verify(listAddressesView).showAddresses(addressList);

    }

    @Test
    public void listAllAddress_notifyListEmpty() {

        listAddresses(Single.error(mock(Exception.class)));

        verify(listAddressesView).showProgress();
        verify(listAddressesView).hideProgress();
        verify(listAddressesView).notifyEmptyList();

    }

    private void listAddresses(Single<List<Address>> just) {
        when(listAddressesUseCase.execute(any(ListAddressesUseCase.Request.class)))
                .thenReturn(just);

        listAddressesPresenter.listAddresses();
    }

}