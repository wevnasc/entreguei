package br.com.wnascimento.entreguei.features.address.search;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.mockito.Mock;

import br.com.wnascimento.entreguei.util.ImmediateScheduler;
import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class SaveAddressUseCaseTest {

    @ClassRule
    public static final ImmediateScheduler schedulers = new ImmediateScheduler();

    @Mock
    private AddressLocalRepository addressLocalRepository;

    private SaveAddressUseCase saveAddressUseCase;

    @Before
    public void setup() {
        initMocks(this);
        saveAddressUseCase = new SaveAddressUseCase(Schedulers.io(), AndroidSchedulers.mainThread(), addressLocalRepository);
    }


    @Test
    public void shouldReturnTheAddressByCpf() {
        when(addressLocalRepository.save(any(Address.class)))
                .thenReturn(Completable.complete());

        saveAddressUseCase.execute(new SaveAddressUseCase.Request(mock(Address.class)));

        verify(addressLocalRepository).save(any(Address.class));
    }

}
