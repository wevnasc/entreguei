package br.com.wnascimento.entreguei.features.address.list;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.mockito.Mock;

import br.com.wnascimento.entreguei.features.address.Address;
import br.com.wnascimento.entreguei.features.address.search.AddressLocalRepository;
import br.com.wnascimento.entreguei.util.ImmediateScheduler;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ListAddressUseCaseTest {

    @ClassRule
    public static final ImmediateScheduler schedulers = new ImmediateScheduler();

    @Mock
    private AddressLocalRepository addressLocalRepository;


    private ListAddressesUseCase listAddressesUseCase;

    @Before
    public void setup() {
        initMocks(this);
        listAddressesUseCase = new ListAddressesUseCase(Schedulers.newThread(), AndroidSchedulers.mainThread(), addressLocalRepository);
    }

    @Test
    public void shouldGetAllAddress() {

        when(addressLocalRepository.getAddresses())
                .thenReturn(Flowable.just(mock(Address.class)));

        listAddressesUseCase.execute(new ListAddressesUseCase.Request())
                .subscribe();

        verify(addressLocalRepository).getAddresses();

    }

}
