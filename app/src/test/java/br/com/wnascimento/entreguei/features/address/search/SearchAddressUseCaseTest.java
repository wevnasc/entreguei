package br.com.wnascimento.entreguei.features.address.search;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.mockito.Mock;

import br.com.wnascimento.entreguei.util.ImmediateScheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class SearchAddressUseCaseTest {

    @ClassRule
    public static final ImmediateScheduler schedulers = new ImmediateScheduler();

    @Mock
    private AddressRemoteRepository addressRemoteRepository;

    private SearchAddressUseCase searchAddressUseCase;

    @Before
    public void setup() {
        initMocks(this);
        searchAddressUseCase = new SearchAddressUseCase(Schedulers.io(), AndroidSchedulers.mainThread(), addressRemoteRepository);
    }

    @Test
    public void shouldReturnTheAddressByCpf() {
        when(addressRemoteRepository.getAddressByCep(anyString()))
                .thenReturn(Single.just(mock(Address.class)));

        searchAddressUseCase.execute(new SearchAddressUseCase.Request(anyString()));

        verify(addressRemoteRepository).getAddressByCep(anyString());
    }

}
